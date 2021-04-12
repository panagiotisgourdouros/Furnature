/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.groupproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

@Entity
@Table(name = "carts")
@NamedQueries({ @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
		@NamedQuery(name = "Cart.findById", query = "SELECT c FROM Cart c WHERE c.id = :id"),
		@NamedQuery(name = "Cart.findByDate", query = "SELECT c FROM Cart c WHERE c.datePlaced = :date"),
		@NamedQuery(name = "Cart.findByTotalDiscount", query = "SELECT c FROM Cart c WHERE c.totalDiscount = :totalDiscount"),
		@NamedQuery(name = "Cart.findByTotalAmount", query = "SELECT c FROM Cart c WHERE c.totalAmount = :totalAmount") })

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "date")
	private LocalDate datePlaced;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation

	@Column(name = "total_discount")
	private BigDecimal totalDiscount;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@JoinColumn(name = "myuser_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Myuser myuser;

	@OneToMany(mappedBy = "cartDetailsPK.cart", cascade = CascadeType.ALL)
	private Set<CartDetails> cartDetails = new HashSet<CartDetails>();

	
	public Cart() {
	}

	public Cart(Integer id) {
		this.id = id;
	}

	public Cart(LocalDate datePlaced, Myuser myuser, BigDecimal totalAmount, BigDecimal totalDiscount) {
		this.datePlaced = datePlaced;
		this.totalDiscount = totalDiscount;
		this.totalAmount = totalAmount;
		this.myuser = myuser;

	}

	public Cart(LocalDate datePlaced, Myuser myuser) {
		
		this.datePlaced = datePlaced;
		this.myuser = myuser;
		this.totalAmount = new BigDecimal(0);
		this.totalDiscount = new BigDecimal(0);
	}

	public Cart(Myuser myuser) {
		this.myuser = myuser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(LocalDate datePlaced) {
		this.datePlaced = datePlaced;
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

	public Myuser getMyuser() {
		return myuser;
	}

	public void setMyuser(Myuser myuser) {
		this.myuser = myuser;
	}

	public Set<CartDetails> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(Set<CartDetails> cartDetails) {
		this.cartDetails = cartDetails;
	}

	@Override
	public String toString() {
		return "Cart{" + "id=" + id + ", datePlaced=" + datePlaced + ", totalDiscount=" + totalDiscount
				+ ", totalAmount=" + totalAmount + ", myuser=" + myuser + ", cartDetails=" + cartDetails + '}';
	}

//
// private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "date")
//    @Temporal(TemporalType.DATE)
//    private Date datePlaced;
//    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Column(name = "total_discount")
//    private BigDecimal totalDiscount;
//    @Column(name = "total_amount")
//    private BigDecimal totalAmount;
//    @JoinColumn(name = "myuser_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Myuser myuser_id;
//    @JoinColumn(name = "status_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Status statusId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carts")
//    private List<CartDetails> cartDetails;
//
//    public Cart() {
//    }
//
//    public Cart(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Date getDatePlaced() {
//        return datePlaced;
//    }
//
//    public void setDatePlaced(Date datePlaced) {
//        this.datePlaced = datePlaced;
//    }
//
//    public BigDecimal getTotalDiscount() {
//        return totalDiscount;
//    }
//
//    public void setTotalDiscount(BigDecimal totalDiscount) {
//        this.totalDiscount = totalDiscount;
//    }
//
//    public BigDecimal getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(BigDecimal totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public Myuser getMyuser_id() {
//        return myuser_id;
//    }
//
//    public void setMyuser_id(Myuser myuser_id) {
//        this.myuser_id = myuser_id;
//    }
//
//    public Status getStatusId() {
//        return statusId;
//    }
//
//    public void setStatusId(Status statusId) {
//        this.statusId = statusId;
//    }
//
//    @XmlTransient
//    public List<CartDetails> getCartDetails() {
//        return cartDetails;
//    }
//
//    public void setCartDetails(List<CartDetails> cartDetails) {
//        this.cartDetails = cartDetails;
//    }
//
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Cart)) {
			return false;
		}
		Cart other = (Cart) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

}
