package team.groupproject.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "orders_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetails.findAll", query = "SELECT o FROM OrderDetails o"),
    @NamedQuery(name = "OrderDetails.findByProductId", query = "SELECT o FROM OrderDetails o WHERE o.ordersDetailsPK.productId = :productId"),
    @NamedQuery(name = "OrderDetails.findByOrderId", query = "SELECT o FROM OrderDetails o WHERE o.ordersDetailsPK.orderId = :orderId"),
    @NamedQuery(name = "OrderDetails.findByQuantity", query = "SELECT o FROM OrderDetails o WHERE o.quantity = :quantity")})
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OrderDetailsPK ordersDetailsPK;

    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;

    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Order orders;

    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product products;

    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsPK ordersDetailsPK) {
        this.ordersDetailsPK = ordersDetailsPK;
    }

    public OrderDetails(OrderDetailsPK ordersDetailsPK, int quantity) {
        this.ordersDetailsPK = ordersDetailsPK;
        this.quantity = quantity;
    }

    public OrderDetails(int productId, int orderId) {
        this.ordersDetailsPK = new OrderDetailsPK(productId, orderId);
    }

    public OrderDetailsPK getOrdersDetailsPK() {
        return ordersDetailsPK;
    }

    public void setOrdersDetailsPK(OrderDetailsPK ordersDetailsPK) {
        this.ordersDetailsPK = ordersDetailsPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordersDetailsPK != null ? ordersDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetails)) {
            return false;
        }
        OrderDetails other = (OrderDetails) object;
        if ((this.ordersDetailsPK == null && other.ordersDetailsPK != null) || (this.ordersDetailsPK != null && !this.ordersDetailsPK.equals(other.ordersDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "team.groupproject.entity.OrdersDetails[ ordersDetailsPK=" + ordersDetailsPK + " ]";
    }

}
