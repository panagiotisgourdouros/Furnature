package team.groupproject.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.entity.Myuser;
import team.groupproject.model.MessageResponse;
import team.groupproject.repository.RoleRepo;
import team.groupproject.repository.UserRepo;
import team.groupproject.security.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @PutMapping(value = "/update")
    @CrossOrigin
    public ResponseEntity<?> updateUser(@Valid @RequestBody Myuser updateUser, Authentication authentication) {
        // get the user through the incoming jwt
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser existingUser = userService.findByUsername(userDetails.getUsername());
        try {
            existingUser = userService.findById(existingUser.getId());
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(" User with id " + existingUser.getId() + " not found.");
        }
        // SET THE NEW FIELDS
        existingUser.setFirstName(updateUser.getFirstName());
        existingUser.setLastName(updateUser.getLastName());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setPhoneNumber(updateUser.getPhoneNumber());
        existingUser.setPaypalAcc(updateUser.getPaypalAcc());
        existingUser.setTitle(updateUser.getTitle());
        userRepo.save(existingUser);
        return ResponseEntity.ok(new MessageResponse("Your Personal Information was successfully updated"));
    }

}
