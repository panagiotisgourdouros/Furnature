package team.groupproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o"),
    @NamedQuery(name = "Order.findById", query = "SELECT o FROM Order o WHERE o.id = :id"),
    @NamedQuery(name = "Order.findByDatePlaced", query = "SELECT o FROM Order o WHERE o.datePlaced = :datePlaced"),
    @NamedQuery(name = "Order.findByDateUpdated", query = "SELECT o FROM Order o WHERE o.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "Order.findByTotalDiscount", query = "SELECT o FROM Order o WHERE o.totalDiscount = :totalDiscount"),
    @NamedQuery(name = "Order.findByTotalAmount", query = "SELECT o FROM Order o WHERE o.totalAmount = :totalAmount")})

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_placed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePlaced;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private List<Payment> paymentsList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderDetails> ordersDetailsList;

    @JoinColumn(name = "ship_address_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Address shipAddressId;

    @JoinColumn(name = "myuser_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Myuser myUser;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @XmlTransient
    public List<Payment> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payment> paymentsList) {
        this.paymentsList = paymentsList;
    }

    @XmlTransient
    public List<OrderDetails> getOrdersDetailsList() {
        return ordersDetailsList;
    }

    public void setOrdersDetailsList(List<OrderDetails> ordersDetailsList) {
        this.ordersDetailsList = ordersDetailsList;
    }

    public Address getShipAddressId() {
        return shipAddressId;
    }

    public void setShipAddressId(Address shipAddressId) {
        this.shipAddressId = shipAddressId;
    }

    public Myuser getMyuser_id() {
        return myUser;
    }

    public void setMyuser_id(Myuser myuser_id) {
        this.myUser = myuser_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "team.groupproject.entity.Orders[ id=" + id + " ]";
    }

}
