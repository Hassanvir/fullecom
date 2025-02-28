package com.example.backendsigninpractice.Repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backendsigninpractice.model.CartItem;
import com.example.backendsigninpractice.model.Product;
import com.example.backendsigninpractice.model.User;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByProduct(Product product);
    Optional<CartItem> findByProductAndUser(Product product, User user);
    Optional<CartItem> findByProductIdAndUser(Long productId, User user);
    List<CartItem> findByUser(User user);
    List<CartItem> findByUserId(Long userId);


    // ✅ Fetch only active cart items (not yet placed in an order)
    @Query("SELECT c FROM CartItem c WHERE c.user = :user AND c.order IS NULL")
    List<CartItem> findActiveCartItems(@Param("user") User user);

}
