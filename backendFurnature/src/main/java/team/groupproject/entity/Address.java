package team.groupproject.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "addresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
    @NamedQuery(name = "Address.findByStreetName", query = "SELECT a FROM Address a WHERE a.streetName = :streetName"),
    @NamedQuery(name = "Address.findByHouseNumber", query = "SELECT a FROM Address a WHERE a.houseNumber = :houseNumber"),
    @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
    @NamedQuery(name = "Address.findByPostalCode", query = "SELECT a FROM Address a WHERE a.postalCode = :postalCode"),
    @NamedQuery(name = "Address.findByCountry", query = "SELECT a FROM Address a WHERE a.country = :country"),
    @NamedQuery(name = "Address.findByBilling", query = "SELECT a FROM Address a WHERE a.billing = :billing"),
    @NamedQuery(name = "Address.findByShipping", query = "SELECT a FROM Address a WHERE a.shipping = :shipping")})

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "street_name")
    @NotEmpty(message = "Street name cannot be empty")
    private String streetName;

    @Basic(optional = false)
    @Column(name = "house_number")
    @Size(min = 1, max = 10, message = "House number can have 1- 10 characters")
    @NotEmpty(message = "House number cannot be empty")
    private String houseNumber;

    @Basic(optional = false)
    @Column(name = "city")
    @NotEmpty(message = "City cannot be empty")
    private String city;

    @Basic(optional = false)
    @Column(name = "postal_code")
    @NotEmpty(message = "Postal Code cannot be empty")
    private String postalCode;

    @Basic(optional = false)
    @Column(name = "country")
    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @Column(name = "billing")
    private Boolean billing;

    @Column(name = "shipping")
    private Boolean shipping;

    @JoinColumn(name = "myuser_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Myuser myuser_id;

    @OneToMany(mappedBy = "shipAddressId")
    private List<Order> ordersList;

    public Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public Address(Boolean billing, String city, String country, String houseNumber, Myuser user, String postalCode, Boolean shipping, String streetName) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.myuser_id = user;
        this.billing = billing;
        this.shipping = shipping;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getBilling() {
        return billing;
    }

    public void setBilling(Boolean billing) {
        this.billing = billing;
    }

    public Boolean getShipping() {
        return shipping;
    }

    public void setShipping(Boolean shipping) {
        this.shipping = shipping;
    }

    public Myuser getMyuser_id() {
        return myuser_id;
    }

    public void setMyuser_id(Myuser myuser_id) {
        this.myuser_id = myuser_id;
    }

    @XmlTransient
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "team.groupproject.entity.Addresses[ id=" + id + " ]";
    }

}
