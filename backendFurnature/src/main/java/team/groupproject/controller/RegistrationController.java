package team.groupproject.controller;

import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.entity.Myuser;
import team.groupproject.model.ErrorDetails;
import team.groupproject.model.MessageResponse;
import team.groupproject.repository.RoleRepo;
import team.groupproject.repository.UserRepo;
import team.groupproject.security.UserService;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping(value = "/register")
    @CrossOrigin
    public ResponseEntity<?> registerUser(@Valid @RequestBody Myuser registrationUser) {

        if (userRepo.existsByUsername(registrationUser.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new ErrorDetails(new Date(), "Already Exists", "username is already taken"));
        }
        if (userRepo.existsByEmail(registrationUser.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new ErrorDetails(new Date(), "Already Exists", "email is already in use"));
        }
        userService.createUser(registrationUser, false);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
