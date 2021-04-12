package team.groupproject.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.converters.OrderConverter;
import team.groupproject.dto.OrdersDto;
import team.groupproject.entity.Cart;
import team.groupproject.entity.Myuser;
import team.groupproject.entity.Order;
import team.groupproject.entity.Status;
import team.groupproject.errorHandling.APIException;
import team.groupproject.repository.OrderRepo;
import team.groupproject.repository.StatusRepo;
import team.groupproject.security.UserService;
import team.groupproject.service.OrderService;
import team.groupproject.service.StatusService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    StatusService statusService;

    @PostMapping("/users/orders")
    public ResponseEntity<OrdersDto> createOrder(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());

        Cart cart = user.getCart();

        OrdersDto ordersDto = OrderConverter.convertOrderToOrderDto(orderService.createOrder(cart));

        return ResponseEntity.ok(ordersDto);

    }

    @GetMapping("/users/orders")
    public ResponseEntity<List<OrdersDto>> getAllOrdersByUser(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Myuser user = userService.findByUsername(userDetails.getUsername());

        List<Order> orders = orderService.getAllOrdersByUser(user.getId());

        List<OrdersDto> ordersDtos = orders.stream().map(OrderConverter::convertOrderToOrderDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ordersDtos);
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrdersDto>> getAllOrders() {

        List<Order> orders = orderService.getAllOrders();

        List<OrdersDto> ordersDtos = orders.stream().map(OrderConverter::convertOrderToOrderDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ordersDtos);
    }

    @PutMapping("/admin/orders/updateStatus/{id}/{statusName}")
    public ResponseEntity<OrdersDto> updateOrderStatus(@PathVariable Integer id, @PathVariable String statusName) {
        Optional<Order> orderOpt = orderRepo.findById(id);
        Status status = statusRepo.findByName(statusName);

        if (status == null) {
            throw new APIException(String.format("Status with Id : %d does not exist", id));
        }

        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            orderRepo.save(order);
            return ResponseEntity.ok(OrderConverter.convertOrderToOrderDto(order));
        } else {
            throw new APIException(String.format("Order with Id : %d does not exist", id));
        }

    }

}
