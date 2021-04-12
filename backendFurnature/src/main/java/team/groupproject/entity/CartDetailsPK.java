package team.groupproject.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;

import javax.persistence.ManyToOne;

@Embeddable
public class CartDetailsPK implements Serializable {

    private Cart cart;
    private Product product;

    public CartDetailsPK() {
    }

    public CartDetailsPK(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cart == null) ? 0 : cart.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CartDetailsPK other = (CartDetailsPK) obj;
        if (cart == null) {
            if (other.cart != null) {
                return false;
            }
        } else if (!cart.equals(other.cart)) {
            return false;
        }
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        return true;
    }

}

