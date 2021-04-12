package team.groupproject.model;

public class ResetPasswordByTokenRequest {

    private String resetPasswordToken;
    private String password;
    private String email;

    public ResetPasswordByTokenRequest(String resetPasswordToken, String password) {
        this.resetPasswordToken = resetPasswordToken;
        this.password = password;
    }

    public ResetPasswordByTokenRequest(String email) {
        this.email = email;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
