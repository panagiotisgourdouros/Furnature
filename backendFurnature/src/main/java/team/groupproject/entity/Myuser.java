package team.groupproject.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "myusers")
@NamedQueries({
    @NamedQuery(name = "Myuser.findAll", query = "SELECT m FROM Myuser m"),
    @NamedQuery(name = "Myuser.findById", query = "SELECT m FROM Myuser m WHERE m.id = :id"),
    @NamedQuery(name = "Myuser.findByUsername", query = "SELECT m FROM Myuser m WHERE m.username = :username"),
    @NamedQuery(name = "Myuser.findByPassword", query = "SELECT m FROM Myuser m WHERE m.password = :password"),
    @NamedQuery(name = "Myuser.findByFirstName", query = "SELECT m FROM Myuser m WHERE m.firstName = :firstName"),
    @NamedQuery(name = "Myuser.findByLastName", query = "SELECT m FROM Myuser m WHERE m.lastName = :lastName"),
    @NamedQuery(name = "Myuser.findByEmail", query = "SELECT m FROM Myuser m WHERE m.email = :email"),
    @NamedQuery(name = "Myuser.findByPhoneNumber", query = "SELECT m FROM Myuser m WHERE m.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Myuser.findByPaypalAcc", query = "SELECT m FROM Myuser m WHERE m.paypalAcc = :paypalAcc"),
    @NamedQuery(name = "Myuser.findByTitle", query = "SELECT m FROM Myuser m WHERE m.title = :title")})
public class Myuser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message = "Username cannot be empty")
    @Column(name = "username")
    private String username;
    
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message = "first name cannot be empty ")
    @Size(min = 2, max = 30, message = "First name should include at least 2 characters")
    @Column(name = "first_name")
    private String firstName;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message = "Last name cannot be empty ")
    @Size(min = 2, max = 30, message = "last name should include at least 2 characters")
    @Column(name = "last_name")
    private String lastName;
    
    @Basic(optional = false)
    @NotEmpty(message = "Email address should not be empty")
    @Email(message = "email address should be valid")
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone_number")
    @NotEmpty(message = "Phone number cannot be empty ")
    private String phoneNumber;
    
    @Column(name = "paypal_acc")
    private String paypalAcc;
    
    @Basic(optional = false)
    @NotEmpty(message = "Title should not be empty")
    @Column(name = "title")
    private String title;
    
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @JoinTable(name = "myusers_roles", joinColumns = {
        @JoinColumn(name = "myuser_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")})

    @ManyToMany
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "myuser_id")
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminId")
    private List<UsedProduct> usedProductsList;

    @OneToMany(mappedBy = "customerId")
    private List<UsedProduct> usedProductsList1;
   
    @OneToOne(mappedBy = "myuser")
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "myUser")
    private List<Order> ordersList;

    public Myuser() {
    }

    public Myuser(Integer id) {
        this.id = id;
    }

    public Myuser(String email, String firstName, String lastName, String password, String paypalAcc, String phoneNumber, String resetPasswordToken, String title, String username) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.title = title;
        this.paypalAcc = paypalAcc;
        this.resetPasswordToken=resetPasswordToken;
    }

        public Myuser(String email, String firstName, String lastName, String password, String paypalAcc, String phoneNumber, String title, String username) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.title = title;
        this.paypalAcc = paypalAcc;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
    
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaypalAcc() {
        return paypalAcc;
    }

    public void setPaypalAcc(String paypalAcc) {
        this.paypalAcc = paypalAcc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    public List<UsedProduct> getUsedProductsList() {
        return usedProductsList;
    }

    public void setUsedProductsList(List<UsedProduct> usedProductsList) {
        this.usedProductsList = usedProductsList;
    }


    public List<UsedProduct> getUsedProductsList1() {
        return usedProductsList1;
    }

    public void setUsedProductsList1(List<UsedProduct> usedProductsList1) {
        this.usedProductsList1 = usedProductsList1;
    }

    public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Myuser)) {
            return false;
        }
        Myuser other = (Myuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "team.groupproject.entity.Myusers[ id=" + id + " ]";
    }

}
