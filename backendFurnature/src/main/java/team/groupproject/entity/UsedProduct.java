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
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "used_products")
@NamedQueries({
    @NamedQuery(name = "UsedProduct.findAll", query = "SELECT u FROM UsedProduct u"),
    @NamedQuery(name = "UsedProduct.findById", query = "SELECT u FROM UsedProduct u WHERE u.id = :id"),
    @NamedQuery(name = "UsedProduct.findByCategory", query = "SELECT u FROM UsedProduct u WHERE u.category = :category"),
    @NamedQuery(name = "UsedProduct.findByOfferAmmount", query = "SELECT u FROM UsedProduct u WHERE u.offerAmmount = :offerAmmount"),
    @NamedQuery(name = "UsedProduct.findByOfferDate", query = "SELECT u FROM UsedProduct u WHERE u.offerDate = :offerDate"),
    @NamedQuery(name = "UsedProduct.findByBuyDate", query = "SELECT u FROM UsedProduct u WHERE u.buyDate = :buyDate")})

public class UsedProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "category")
    private String category;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "offer_ammount")
    private BigDecimal offerAmmount;

    @Basic(optional = false)
    @Column(name = "offer_date")
    @Temporal(TemporalType.DATE)
    private Date offerDate;

    @Column(name = "buy_date")
    @Temporal(TemporalType.DATE)
    private Date buyDate;

    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Myuser adminId;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne
    private Myuser customerId;

    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usedProductId")
    private List<UsedImage> usedImagesList;

    public UsedProduct() {
    }

    public UsedProduct(Integer id) {
        this.id = id;
    }

    public UsedProduct(Integer id, String category, BigDecimal offerAmmount, Date offerDate) {
        this.id = id;
        this.category = category;
        this.offerAmmount = offerAmmount;
        this.offerDate = offerDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getOfferAmmount() {
        return offerAmmount;
    }

    public void setOfferAmmount(BigDecimal offerAmmount) {
        this.offerAmmount = offerAmmount;
    }

    public Date getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(Date offerDate) {
        this.offerDate = offerDate;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Myuser getAdminId() {
        return adminId;
    }

    public void setAdminId(Myuser adminId) {
        this.adminId = adminId;
    }

    public Myuser getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Myuser customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlTransient
    public List<UsedImage> getUsedImagesList() {
        return usedImagesList;
    }

    public void setUsedImagesList(List<UsedImage> usedImagesList) {
        this.usedImagesList = usedImagesList;
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
        if (!(object instanceof UsedProduct)) {
            return false;
        }
        UsedProduct other = (UsedProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "team.groupproject.entity.UsedProducts[ id=" + id + " ]";
    }

}
