package team.groupproject.entity;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cart_details")
@AssociationOverrides({
    @AssociationOverride(name = "cartDetailsPK.cart", joinColumns = @JoinColumn(name = "cart_id")),
    @AssociationOverride(name = "cartDetailsPK.product", joinColumns = @JoinColumn(name = "product_id"))})
@NamedQueries({
    @NamedQuery(name = "CartDetails.findAll", query = "SELECT c FROM CartDetails c"),
    @NamedQuery(name = "CartDetails.findByCart", query = "SELECT c FROM CartDetails c WHERE c.cartDetailsPK.cart = :cart"),
    @NamedQuery(name = "CartDetails.findByProductId", query = "SELECT c FROM CartDetails c WHERE c.cartDetailsPK.product = :product"),
    @NamedQuery(name = "CartDetails.findByQuantity", query = "SELECT c FROM CartDetails c WHERE c.quantity = :quantity")})

public class CartDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private CartDetailsPK cartDetailsPK = new CartDetailsPK();

    private int quantity;

    public CartDetails() {
    }

    public CartDetails(CartDetailsPK cartDetailsPK) {
        this.cartDetailsPK = cartDetailsPK;
    }

    public CartDetails(CartDetailsPK cartDetailsPK, int quantity) {
        this.cartDetailsPK = cartDetailsPK;
        this.quantity = quantity;
    }

    @EmbeddedId
    public CartDetailsPK getCartDetailsPK() {
        return cartDetailsPK;
    }

    public void setCartDetailsPK(CartDetailsPK cartDetailsPK) {
        this.cartDetailsPK = cartDetailsPK;
    }

    @Basic(optional = false)
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Cart getCart() {
        return getCartDetailsPK().getCart();
    }

    public void setCart(Cart cart) {
        getCartDetailsPK().setCart(cart);
    }

    @Transient
    public Product getProduct() {
        return getCartDetailsPK().getProduct();
    }

    public void setProduct(Product product) {
        getCartDetailsPK().setProduct(product);
    }

}
