package team.groupproject.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Cart;
import team.groupproject.entity.Myuser;

import team.groupproject.repository.CartRepo;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public Cart findById(int id) {
        return cartRepo.findById(id);
    }

    @Override
    public Cart createCart(Myuser myuser) {

        Cart cart = new Cart(LocalDate.now(), myuser);
        cartRepo.save(cart);
        return cart;
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepo.save(cart);
    }

    @Override
    public void removeCart(Cart cart) {

        cartRepo.delete(cart);
    }

}
