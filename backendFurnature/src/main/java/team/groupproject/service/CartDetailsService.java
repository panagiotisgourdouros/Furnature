package team.groupproject.service;

import org.springframework.stereotype.Service;
import team.groupproject.entity.Cart;
import team.groupproject.entity.CartDetails;
import team.groupproject.entity.CartDetailsPK;
import team.groupproject.entity.Product;

@Service
public interface CartDetailsService {

    public void createCartDetails(Cart cart, Product product);

    public boolean existsByCartDetailsPK(CartDetailsPK cartDetailsPK);

    public CartDetails findByCartDetailsPK(CartDetailsPK cartDetailsPK);

    public void save(CartDetails cartDetails);

    public void deleteProductFromCart(CartDetails cartDetails);

    public int getTotalCartQuantity(Integer id);
}
