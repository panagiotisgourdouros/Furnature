package team.groupproject.service;

import java.util.List;
import team.groupproject.entity.Cart;
import team.groupproject.entity.Order;

public interface OrderService {

    Order createOrder(Cart cart);

    List<Order> getAllOrdersByUser(int userId);

    List<Order> getAllOrders();

}
