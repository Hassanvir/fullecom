package com.example.backendsigninpractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.backendsigninpractice.model.CartItem;
import com.example.backendsigninpractice.model.Order;
import com.example.backendsigninpractice.model.PurchasedItem;
import com.example.backendsigninpractice.model.User;
import com.example.backendsigninpractice.Repo.CartRepository;
import com.example.backendsigninpractice.Repo.OrderRepository;
import com.example.backendsigninpractice.Repo.PurchasedItemrepository;
import com.example.backendsigninpractice.Repo.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class OrderController {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final PurchasedItemrepository purchasedItemRepository;
    private final UserRepository userRepository;  // Add UserRepository to fetch user

    @PostMapping("/place")
    @Transactional
    public Order placeOrder() {
        // 1️⃣ Get the currently authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Fetch the user's cart items (assuming cart is user-specific)
        List<CartItem> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place order.");
        }

        // 3️⃣ Calculate the total price
        double totalPrice = cartItems.stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        // 4️⃣ Create and save the order with the associated user
        Order order = Order.builder()
                .user(user)  // Associate user here 👈
                .cartItems(cartItems)
                .totalPrice(totalPrice)
                .build();
        orderRepository.save(order);

        // 5️⃣ Save purchased items
        for (CartItem cartItem : cartItems) {
            PurchasedItem purchasedItem = PurchasedItem.builder()
                    .user(user)
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .order(order)
                    .price(cartItem.getProduct().getPrice())
                    .build();
            purchasedItemRepository.save(purchasedItem);
        }

        // 6️⃣ Clear the cart after placing the order
        cartRepository.deleteAll(cartItems);
        cartRepository.flush();

        return order;
    }

    @GetMapping
    public List<Order> getOrders() {

   // 1️⃣ Get authenticated user
   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   String username = auth.getName();
   User user = userRepository.findByUsername(username)
           .orElseThrow(() -> new RuntimeException("User not found"));
        //    return orderRepository.findAll();



   // 2️⃣ Retrieve all orders placed by the user, ensuring cart items are included
   return orderRepository.findByUser(user);


        // return orderRepository.findAllWithCartItems();
    
    }



// @GetMapping("/user/{userId}")
//  public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
//         User user = new User();
//         user.setId(userId);
//         List<Order> orders = orderRepository.findAllByUser(user);
//         return ResponseEntity.ok(orders);
//  }




//  ✅ Helper to fetch current user from authentication
//  private User getCurrentUser(Authentication authentication) {
//     String username = authentication.getName();
//     return userRepository.findByUsername(username)
//             .orElseThrow(() -> new RuntimeException("User not found"));
// }


//  @GetMapping("/cart")
//     public List<CartItem> getCartItems(Authentication authentication) {
//         User user = getCurrentUser(authentication);

//         return cartRepository.findAll().stream()
//                 .filter(cartItem -> cartItem.getOrder() == null && cartItem.getUser().equals(user))
//                 .collect(Collectors.toList());
//     }   

}
