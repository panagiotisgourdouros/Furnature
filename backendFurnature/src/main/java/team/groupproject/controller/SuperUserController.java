package team.groupproject.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.converters.MyUserConverter;
import team.groupproject.dto.MyUserDto;
import team.groupproject.entity.Myuser;
import team.groupproject.entity.Role;
import team.groupproject.errorHandling.APIException;
import team.groupproject.model.ErrorDetails;
import team.groupproject.model.MessageResponse;
import team.groupproject.repository.UserRepo;
import team.groupproject.security.UserService;

@RestController
@RequestMapping("/api/superuser")
@CrossOrigin
public class SuperUserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/admin")
    @CrossOrigin
    public ResponseEntity<List<MyUserDto>> getAllAdminUsers(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());
        List<Role> roles = user.getRoles();
        boolean isSuperUser = roles.stream().anyMatch(r -> r.getRoleName().equals("superuser"));
        if (isSuperUser) {
            List<Myuser> myusers = userRepo.findAll();
            List<Myuser> admins = myusers.stream()
                    .filter(u -> u.getRoles().stream().anyMatch(r -> r.getRoleName().equals("admin")))
                    .collect(Collectors.toList());
            List<MyUserDto> adminsDtos = admins.stream().map(MyUserConverter::convertMyUserToMyUserDto).collect(Collectors.toList());
            return ResponseEntity.ok(adminsDtos);
        } else {
            throw new BadCredentialsException("User is not allowed to perform such action");
        }
    }

    @PutMapping(value = "/updateAdmin")
    @CrossOrigin
    public ResponseEntity<?> updateAdminUser(@Valid @RequestBody Myuser adminToUpdate, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());

        if (user != null) {

            List<Role> roles = user.getRoles();

            boolean isSuperUser = roles.stream().anyMatch(r -> r.getRoleName().equals("superuser"));

            if (isSuperUser) {
                
                if (!userRepo.existsByUsername(adminToUpdate.getUsername())) {

                    return ResponseEntity.badRequest().body(new ErrorDetails(new Date(), "Admin does not exist"));
                }

                if (userRepo.existsByEmailAndUsernameNot(adminToUpdate.getEmail(), adminToUpdate.getUsername())) {
                    return ResponseEntity.badRequest().body(new ErrorDetails(new Date(), "Email already exists for another User"));
                }
                Myuser userToBeUpdated = userRepo.findByUsername(adminToUpdate.getUsername());

                userToBeUpdated.setFirstName(adminToUpdate.getFirstName());
                userToBeUpdated.setLastName(adminToUpdate.getLastName());
                userToBeUpdated.setEmail(adminToUpdate.getEmail());
                userToBeUpdated.setPhoneNumber(adminToUpdate.getPhoneNumber());
                userToBeUpdated.setTitle(adminToUpdate.getTitle());

                userRepo.save(userToBeUpdated);
                return ResponseEntity.ok(new MessageResponse("User  Updated successfully!"));
            } else {
                throw new BadCredentialsException("User is not allowed to perform such action");
            }
        } else {

            throw new APIException("Back end Failed");
        }
    }

    @PostMapping(value = "/createAdmin")
    @CrossOrigin
    public ResponseEntity<?> registerAdminUser(@Valid @RequestBody Myuser registrationUser,
            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());
        List<Role> roles = user.getRoles();

        boolean isSuperUser = roles.stream().anyMatch(r -> r.getRoleName().equals("superuser"));

        if (isSuperUser) {
            if (userRepo.existsByUsername(registrationUser.getUsername())) {
                return ResponseEntity.badRequest()
                        .body(new ErrorDetails(new Date(), "Already Exists", "username is already taken"));
            }
            if (userRepo.existsByEmail(registrationUser.getEmail())) {
                return ResponseEntity.badRequest()
                        .body(new ErrorDetails(new Date(), "Already Exists", "email is already in use"));
            }
            userService.createUser(registrationUser, true);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } else {
            throw new BadCredentialsException("User is not allowed to perform such action");
        }
    }

    @Transactional
    @DeleteMapping(value = "/deleteAdmin/{username}")
    @CrossOrigin
    public ResponseEntity<?> deleteAdminUser(@PathVariable String username, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());
        List<Role> roles = user.getRoles();

        boolean isSuperUser = roles.stream().anyMatch(r -> r.getRoleName().equals("superuser"));

        if (isSuperUser) {
            Myuser userToBeDeleted = userRepo.findByUsername(username);
            List<Role> userToBeDeletedRoles = userToBeDeleted.getRoles();
            
            if (userToBeDeleted == null) {
                return ResponseEntity.badRequest().body(new ErrorDetails(new Date(), "User does not exist"));
            }
            boolean userToBeDeletedIsAdmin = roles.stream().anyMatch(r -> r.getRoleName().equals("admin"));

            if (userToBeDeletedIsAdmin) {
                userToBeDeletedRoles.removeIf(role -> role.getId() == 2);
                return ResponseEntity.ok(new MessageResponse("Administrator successfully deleted!"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("User to be deleted is not admin"));
            }
        } else {
            throw new BadCredentialsException("User is not allowed to perform such action");
        }
    }

}
