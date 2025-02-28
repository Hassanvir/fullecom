package com.example.backendsigninpractice.services;


import com.example.backendsigninpractice.model.User;

public interface UserService {
    
    User getUser(Long id);
    User saveUser(User user);
}
