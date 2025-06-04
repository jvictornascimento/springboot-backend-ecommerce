package com.jvictornascimento.buynowdotcom.service.order;

import com.jvictornascimento.buynowdotcom.dtos.OrderDto;
import com.jvictornascimento.buynowdotcom.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    List<Order> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);

    List<OrderDto> getConvertedOrders(List<Order> orders);
}
