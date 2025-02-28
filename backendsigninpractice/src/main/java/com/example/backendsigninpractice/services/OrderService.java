package com.example.backendsigninpractice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendsigninpractice.Repo.CartRepository;
import com.example.backendsigninpractice.Repo.OrderRepository;
import com.example.backendsigninpractice.model.CartItem;
import com.example.backendsigninpractice.model.Order;
import com.example.backendsigninpractice.model.User;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartItemRepository;
    }

    public Order createOrder(User user, List<CartItem> cartItems) {
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(
            cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum()
        );

        for (CartItem item : cartItems) {
            item.setOrder(order);  // ✅ Link each CartItem to this Order
        }

        order.setCartItems(cartItems);

        // ✅ Persist the order and associated cart items
        return orderRepository.save(order);
    }
}
