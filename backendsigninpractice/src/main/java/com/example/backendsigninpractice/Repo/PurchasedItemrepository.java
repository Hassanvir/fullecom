package com.example.backendsigninpractice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendsigninpractice.model.PurchasedItem;

@Repository
public interface PurchasedItemrepository extends JpaRepository<PurchasedItem,Long> {
    
}