package team.groupproject.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.SendFailedException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import team.groupproject.entity.Myuser;
import team.groupproject.model.MessageResponse;
import team.groupproject.model.ResetPasswordByTokenRequest;
import team.groupproject.repository.UserRepo;
import team.groupproject.security.UserService;
import team.groupproject.utility.UrlUtils;

@RestController
@RequestMapping("/reset")
public class ForgotPasswordController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @PostMapping(value = "/forgot_password")
    @CrossOrigin
    public ResponseEntity<?> processForgotPasswordForm(@RequestBody ResetPasswordByTokenRequest request) {

        Myuser myuserFromDB = userService.findByEmail(request.getEmail());
        if (myuserFromDB == null) {
            throw new ResourceNotFoundException("Wrong email");
        }
        System.out.println(myuserFromDB.toString());
        String username = myuserFromDB.getUsername();
        String token = RandomString.make(45);
        System.out.println("Random generated token: " + token);
        userService.updateResetPasswordToken(token, myuserFromDB);
        String baseUrl = UrlUtils.getSiteURL();

        String resetPasswordLink = baseUrl + "/reset/reset_password_form/" + token;

        String subject = "Password Reset from team.groupproject";
        String text = "Follow the link bellow to reset your password for username: " + username + ": " + "\n"
                + resetPasswordLink + "\n" + "\n" + "Ignore this if you remember your password";
        String email = request.getEmail();
        try {
            sentEmail(email, subject, text);
        } catch (SendFailedException ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ResponseEntity.ok(new MessageResponse("We have sent an email to: " + email));

    }

    @GetMapping(value = "/reset_password_form/{token}")
    @CrossOrigin
    public RedirectView showResetPasswordForm(@PathVariable(value = "token") String token,
            RedirectAttributes attributes) {
        System.out.println(">>>>>" + userRepo.existsByResetPasswordToken(token));
        if (userService.existsByResetPasswordToken(token) == false) {
            return new RedirectView("http://127.0.0.1:5500/resetpass-form-error.html");
        }
        attributes.addAttribute("reset_password_token", token);
        return new RedirectView("http://127.0.0.1:5500/html/resetpass-form.html");
    }

    @PostMapping(value = "/reset_password_by_token")
    @CrossOrigin
    public ResponseEntity<?> reset_password_by_token(@RequestBody ResetPasswordByTokenRequest request) {

        String resetPasswordToken = request.getResetPasswordToken();
        if (resetPasswordToken == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("no token sent"));
        }
        if (userRepo.existsByResetPasswordToken(resetPasswordToken)) {
            Myuser myuserFromDB = userService.findByResetPasswordToken(resetPasswordToken);
            String newPassword = request.getPassword();
            System.out.println(newPassword);
            myuserFromDB.setPassword(newPassword);
            userService.save(myuserFromDB);
        }
        return ResponseEntity.ok(new MessageResponse("Your password has been changed"));
    }

    private void sentEmail(String email, String subject, String text) throws SendFailedException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("taniakarageorgi@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }
}
