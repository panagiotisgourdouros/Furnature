package team.groupproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.CartDetails;
import team.groupproject.entity.CartDetailsPK;

@Repository
public interface CartDetailsRepo extends JpaRepository<CartDetails, CartDetailsPK> {

    public boolean existsByCartDetailsPK(CartDetailsPK cartDetailsPK);

    public CartDetails findByCartDetailsPK(CartDetailsPK cartDetailsPK);

    @Query(value = "select carts.id as cartId, products.sku as sku,  products_images.image_path, products.name as productName, products.description as productDescription, products.price as productPrice, cart_details.quantity as productQuantity, carts.total_discount as totalDiscount, "
            + "carts.total_amount as totalAmount, products.id" + " from carts"
            + " inner join cart_details on carts.id=cart_details.cart_id"
            + " inner join products on products.id=cart_details.product_id"
            + " inner join products_images on products.id=products_images.product_id" + " where carts.myuser_id=?1" /* + " group by products.id" */,
            nativeQuery = true)

    public List<Object> getCartInfo(Integer id);

    @Query(value = " select sum(quantity) from cart_details where cart_id=?1", nativeQuery = true)
    public int getTotalCartQuantity(Integer id);
}
