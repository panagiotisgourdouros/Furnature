package team.groupproject.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Address;
import team.groupproject.entity.Cart;
import team.groupproject.entity.Myuser;
import team.groupproject.entity.Order;
import team.groupproject.entity.OrderDetails;
import team.groupproject.entity.Status;
import team.groupproject.repository.CartRepo;
import team.groupproject.repository.OrderDetailsRepo;
import team.groupproject.repository.OrderRepo;
import team.groupproject.repository.StatusRepo;
import team.groupproject.converters.OrderConverter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Transactional
    @Override
    public Order createOrder(Cart cart) {

        Order order = new Order();

        order.setDatePlaced(new Date());

        order.setDateUpdated(new Date());

        order.setMyuser_id(cart.getMyuser());

        order.setOrdersDetailsList(new ArrayList<>());

        Address address = cart.getMyuser().getAddresses().stream().filter(Address::getShipping).findAny().orElse(null);

        order.setShipAddressId(address);

        Status status = statusRepo.findByName("completed");

        order.setStatus(status);

        order.setTotalAmount(cart.getTotalAmount());

        order.setTotalDiscount(cart.getTotalDiscount());

        // TODO Bring Payment List
        order.setPaymentsList(new ArrayList<>());

        orderRepo.save(order);

        List<OrderDetails> orderDetails = cart.getCartDetails().stream()
                .map(c -> OrderConverter.convertCartDetailsToOrderDetails(c, order)).collect(Collectors.toList());

        order.setOrdersDetailsList(orderDetails);

        orderRepo.save(order);

        renewCart(cart);

        return order;

    }

    private void renewCart(Cart cart) {
        Myuser user = cart.getMyuser();
        cartService.removeCart(cart);
        cartService.createCart(user);
    }

    public List<Order> getAllOrdersByUser(int userId) {
        return orderRepo.findByMyUserId(userId);

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
