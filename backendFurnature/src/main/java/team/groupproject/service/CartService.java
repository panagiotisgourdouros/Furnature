package team.groupproject.service;

import org.springframework.stereotype.Service;
import team.groupproject.entity.Cart;
import team.groupproject.entity.Myuser;

@Service
public interface CartService {

    public Cart findById(int id);

    public Cart createCart(Myuser myuser);

    public void saveCart(Cart cart);

    void removeCart(Cart cart);
}
