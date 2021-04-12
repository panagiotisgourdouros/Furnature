package team.groupproject.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.entity.Myuser;
import team.groupproject.model.AuthenticationRequest;
import team.groupproject.model.AuthenticationResponse;
import team.groupproject.repository.UserRepo;
import team.groupproject.security.UserService;
import team.groupproject.utility.JwtUtils;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    @CrossOrigin
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest authenticatonRequest) {
        Authentication authentication = null;
        try {
            //authenticate the user and put it in an authentication object
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticatonRequest.getUsername(), authenticatonRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorect Username or Password!");
        }
        // put the authenticated object in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //generate a jwt token
        String jwt = jwtUtils.generateToken(authentication);
        //get the user detail object( getPrincipal() returns user details object - same as loadByUserName
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getId(), user.getTitle(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getPaypalAcc(), user.getRoles(), user.getUsername()));
    }

}
