// package com.example.backendsigninpractice;


// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.example.backendsigninpractice.Repo.ProductRepository;
// import com.example.backendsigninpractice.model.Product;

// import lombok.RequiredArgsConstructor;
// import java.util.List;

// @Component
// @RequiredArgsConstructor
// public class DataSeeder implements CommandLineRunner {

//     private final ProductRepository productRepository;

//     @Override
//     public void run(String... args) throws Exception {
//         if (productRepository.count() == 0) { 
//             List<Product> products = List.of(
//                     Product.builder().name("iPhone 14").price(999.99).build(),
//                     Product.builder().name("Samsung Galaxy S23").price(899.99).build(),
//                     Product.builder().name("MacBook Pro").price(1299.99).build(),
//                     Product.builder().name("Sony Headphones").price(199.99).build(),
//                     Product.builder().name("Smart Watch").price(299.99).build());
//             productRepository.saveAll(products);
//             System.out.println(" Dummy products added to database!");
//         } else {
//             System.out.println(" Products already exist. Skipping seeding.");
//         }
//     }
// }
