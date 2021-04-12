package team.groupproject.model;

import java.util.List;
import team.groupproject.entity.Role;

public class AuthenticationResponse {

    private String jwtToken;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String paypalAcc;
    private String phoneNumber;
    private String username;
    private List<Role> roles;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwtToken, int id, String title, String firstName, String lastName,
            String email, String phoneNumber, String paypalAcc, List<Role> roles, String username) {
        this.jwtToken = jwtToken;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.title = title;
        this.paypalAcc = paypalAcc;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaypalAcc() {
        return paypalAcc;
    }

    public void setPaypalAcc(String paypalAcc) {
        this.paypalAcc = paypalAcc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
