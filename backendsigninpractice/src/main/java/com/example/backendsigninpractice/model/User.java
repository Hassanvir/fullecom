package com.example.backendsigninpractice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "user cannot be blank")
    @NonNull
    @Column(unique=true,nullable=false)

    private String username;
    
    @NotBlank(message = "email cannot be blank")
    @NonNull
    @Column(unique=true,nullable=false)

    private String email;

    @NotBlank(message = "password cannot be blank")
    @NonNull
    @Column(nullable=false)
    
    private String password;

}