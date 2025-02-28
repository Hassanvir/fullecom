package com.example.backendsigninpractice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.backendsigninpractice.model.CartItem;
import com.example.backendsigninpractice.model.Product;
import com.example.backendsigninpractice.model.User;
import com.example.backendsigninpractice.Repo.CartRepository;
import com.example.backendsigninpractice.Repo.ProductRepository;
import com.example.backendsigninpractice.Repo.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // ✅ Helper to fetch current user from authentication
    private User getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public List<CartItem> getCartItems(Authentication authentication) {
        User user = getCurrentUser(authentication);

        return cartRepository.findAll().stream()
                .filter(cartItem -> cartItem.getOrder() == null && cartItem.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @PostMapping("/add/{productId}")
    public CartItem addToCart(@PathVariable Long productId, Authentication authentication) {
        User user = getCurrentUser(authentication);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingCartItem = cartRepository.findByProductAndUser(product, user);

        CartItem cartItem = existingCartItem.orElseGet(() -> CartItem.builder()
                .product(product)
                .quantity(0)
                .user(user)
                .build());

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        return cartRepository.save(cartItem);
    }

    @PutMapping("/increase/{productId}")
    public CartItem increaseCartItem(@PathVariable Long productId, Authentication authentication) {
        User user = getCurrentUser(authentication);

        CartItem cartItem = cartRepository.findByProductIdAndUser(productId, user)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        return cartRepository.save(cartItem);
    }

    @PutMapping("/decrease/{productId}")
    public CartItem decreaseCartItem(@PathVariable Long productId, Authentication authentication) {
        User user = getCurrentUser(authentication);

        CartItem cartItem = cartRepository.findByProductIdAndUser(productId, user)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            return cartRepository.save(cartItem);
        } else {
            cartRepository.delete(cartItem);
            return null;
        }
    }

    @DeleteMapping("/remove/{cartItemId}")
    public void removeFromCart(@PathVariable Long cartItemId, Authentication authentication) {
        User user = getCurrentUser(authentication);
        cartRepository.findById(cartItemId)
                .filter(item -> item.getUser().equals(user))
                .ifPresent(cartRepository::delete);
    }

    @DeleteMapping("/clear")
    public void clearCart(Authentication authentication) {
        User user = getCurrentUser(authentication);
        List<CartItem> userCartItems = cartRepository.findByUser(user);
        cartRepository.deleteAll(userCartItems);
    }
}
