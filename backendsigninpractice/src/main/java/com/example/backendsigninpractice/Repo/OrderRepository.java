package com.example.backendsigninpractice.Repo;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backendsigninpractice.model.Order;
import com.example.backendsigninpractice.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    // List<Order> findByUser(User user);
    // List<Order> findByUserId(Long userId);

    // @Query("SELECT o FROM Order o LEFT JOIN FETCH o.cartItems")
    // List<Order> findAllWithCartItems(@Param("user"));

    // @EntityGraph(attributePaths = "cartItems")
    // List<Order> findAllByUser(@Param("user")User user);
    @EntityGraph(attributePaths = {"cartItems", "cartItems.product"}) // Ensure cartItems are fetched
    List<Order> findByUser(User user);
}

