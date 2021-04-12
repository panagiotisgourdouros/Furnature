package team.groupproject.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findBySku", query = "SELECT p FROM Product p WHERE p.sku = :sku"),
    @NamedQuery(name = "Product.findByDiscount", query = "SELECT p FROM Product p WHERE p.discount = :discount"),
    @NamedQuery(name = "Product.findByStyle", query = "SELECT p FROM Product p WHERE p.style = :style"),
    @NamedQuery(name = "Product.findByDateCreated", query = "SELECT p FROM Product p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "Product.findByDateUpdated", query = "SELECT p FROM Product p WHERE p.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category = :category"),
    @NamedQuery(name = "Product.findByStock", query = "SELECT p FROM Product p WHERE p.stock = :stock")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @Column(name = "description")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 75)
    private String description;

    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;

    @Basic(optional = false)
    @Column(name = "sku")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 10)
    private String sku;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @Basic(optional = false)
    @Column(name = "stock")
    private int stock;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Style style;

    @JoinTable(name = "products_materials", joinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "material_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Material> materials;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<ProductsImages> productsImages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<OrderDetails> ordersDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartDetailsPK.product")
    private Set<CartDetails> cartDetails = new HashSet<CartDetails>();

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, String description, BigDecimal price, String sku, BigDecimal discount,
            Date dateCreated, Date dateUpdated, Category category, Style style, int stock, List<Material> materials,
            List<ProductsImages> productsImages, List<OrderDetails> ordersDetails) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.discount = discount;
        this.style = style;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.category = category;
        this.style = style;
        this.stock = stock;
        this.materials = materials;
        this.productsImages = productsImages;
        this.ordersDetails = ordersDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<ProductsImages> getProductsImages() {
        return productsImages;
    }

    public void setProductsImages(List<ProductsImages> productsImages) {
        this.productsImages = productsImages;
    }

    public List<OrderDetails> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(List<OrderDetails> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    public Set<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(Set<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
