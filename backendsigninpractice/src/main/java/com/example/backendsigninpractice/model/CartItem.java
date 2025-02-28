package com.example.backendsigninpractice.model;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne  
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference 
    private Order order;

    @ManyToOne(optional = false)
    // @JoinColumn(name = "user_id")
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)  
    private User user;

}