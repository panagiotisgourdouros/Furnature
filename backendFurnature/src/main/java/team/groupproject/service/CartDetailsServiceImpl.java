package team.groupproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Cart;
import team.groupproject.entity.CartDetails;
import team.groupproject.entity.CartDetailsPK;
import team.groupproject.entity.Product;
import team.groupproject.repository.CartDetailsRepo;

@Service
public class CartDetailsServiceImpl implements CartDetailsService {

    @Autowired
    private CartDetailsRepo cartDetailsRepo;

    @Override
    public void createCartDetails(Cart cart, Product product) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setCart(cart);
        cartDetails.setProduct(product);
        cartDetails.setQuantity(1);
        cartDetailsRepo.save(cartDetails);

    }

    @Override
    public void save(CartDetails cartDetails) {
        cartDetailsRepo.save(cartDetails);
    }

    @Override
    public boolean existsByCartDetailsPK(CartDetailsPK cartDetailsPK) {
        return cartDetailsRepo.existsByCartDetailsPK(cartDetailsPK);
    }

    @Override
    public CartDetails findByCartDetailsPK(CartDetailsPK cartDetailsPK) {
        return cartDetailsRepo.findByCartDetailsPK(cartDetailsPK);
    }

    @Override
    public void deleteProductFromCart(CartDetails cartDetails) {
        cartDetailsRepo.delete(cartDetails);
    }

    @Override
    public int getTotalCartQuantity(Integer id) {
        return cartDetailsRepo.getTotalCartQuantity(id);
    }

}
