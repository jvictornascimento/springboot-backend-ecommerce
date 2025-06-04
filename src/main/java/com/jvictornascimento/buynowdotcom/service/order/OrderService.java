package com.jvictornascimento.buynowdotcom.service.order;

import com.jvictornascimento.buynowdotcom.dtos.OrderDto;
import com.jvictornascimento.buynowdotcom.enums.OrderStatus;
import com.jvictornascimento.buynowdotcom.model.*;
import com.jvictornascimento.buynowdotcom.repository.OrderRepository;
import com.jvictornascimento.buynowdotcom.repository.ProductRepository;
import com.jvictornascimento.buynowdotcom.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ICartService cartService;
    private final ModelMapper modelMapper;

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemsList = createOrderItems(order,cart);
        order.setOrderItems(new HashSet<>(orderItemsList));
        order.setTotalAmount(calculateTotalAmount(orderItemsList));
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());
        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }
    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream()
                .map(cartItem ->{
                    Product product = cartItem.getProduct();
                    product.setInventory(product.getInventory() - cartItem.getQuantity());
                    productRepository.save(product);
                    return new OrderItem(
                            order,
                            product,
                            cartItem.getUnitPrice(),
                            cartItem.getQuantity());
                }).toList();
    }
    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemsList) {
        return orderItemsList.stream().map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add );
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    @Override
    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
    @Override
    public List<OrderDto> getConvertedOrders(List<Order> orders) {
        List<OrderDto> orderDtos = orders.stream()
                .map(order -> (modelMapper.map(order,OrderDto.class)))
                .toList();
        return orderDtos;
    }

}
