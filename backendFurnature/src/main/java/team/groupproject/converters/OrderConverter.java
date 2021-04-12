package team.groupproject.converters;

import java.util.List;
import java.util.stream.Collectors;
import team.groupproject.dto.OrderDetailsDto;
import team.groupproject.dto.OrdersDto;
import team.groupproject.dto.ShipAddressDto;
import team.groupproject.dto.StatusDto;
import team.groupproject.entity.CartDetails;
import team.groupproject.entity.Order;
import team.groupproject.entity.OrderDetails;
import team.groupproject.entity.OrderDetailsPK;

public class OrderConverter {

    public static OrderDetails convertCartDetailsToOrderDetails(CartDetails cartDetails, Order order) {

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQuantity(cartDetails.getQuantity());
        orderDetails.setProducts(cartDetails.getProduct());
        orderDetails.setOrdersDetailsPK(
                new OrderDetailsPK(cartDetails.getCartDetailsPK().getProduct().getId(), order.getId()));
        orderDetails.setOrders(order);
        return orderDetails;
    }

    public static OrdersDto convertOrderToOrderDto(Order order) {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setDatePlaced(order.getDatePlaced());
        ordersDto.setDateUpdated(order.getDateUpdated());
        ordersDto.setId(order.getId());

        ordersDto.setShipAddressDto(new ShipAddressDto(order.getShipAddressId().getStreetName(),
                order.getShipAddressId().getHouseNumber(), order.getShipAddressId().getCity(),
                order.getShipAddressId().getPostalCode(), order.getShipAddressId().getCountry()));

        ordersDto.setStatus(new StatusDto(order.getStatus().getId(), order.getStatus().getName()));
        ordersDto.setTotalAmount(order.getTotalAmount());
        ordersDto.setTotalDiscount(order.getTotalDiscount());

        List<OrderDetailsDto> orderDetailsDtoList = order.getOrdersDetailsList().stream()
                .map(o -> new OrderDetailsDto(o.getQuantity(), ProductConverter.convertToProductDto(o.getProducts())))
                .collect(Collectors.toList());
        ordersDto.setOrdersDetailsDtoList(orderDetailsDtoList);

        return ordersDto;

    }

}
