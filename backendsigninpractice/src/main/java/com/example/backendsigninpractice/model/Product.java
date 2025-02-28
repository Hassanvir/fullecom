package com.example.backendsigninpractice.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Generates Getters, Setters, toString, EqualsAndHashCode
@NoArgsConstructor // Generates a No-Args Constructor
@AllArgsConstructor // Generates an All-Args Constructor
@Builder // Allows easy object creation with builder pattern
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private double price;
    
    //   @ManyToOne(optional = false)
    // @JoinColumn(name = "user_id", referencedColumnName = "id")  // foreign key colum user_id
    // private User user;
}
