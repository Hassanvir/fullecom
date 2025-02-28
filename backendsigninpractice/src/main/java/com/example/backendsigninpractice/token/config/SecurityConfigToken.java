// package com.example.backendsigninpractice.token.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.example.backendsigninpractice.token.filter.JwtRequestFilter;

// // @Configuration
// public class SecurityConfigToken {

//     private final JwtRequestFilter jwtRequestFilter;

//     public SecurityConfigToken(JwtRequestFilter jwtRequestFilter) {
//         this.jwtRequestFilter = jwtRequestFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.csrf(csrf -> csrf.disable()) // Disable CSRF for stateless authentication
//                 .authorizeHttpRequests(authorizeRequests ->
//                         authorizeRequests
//                                 .requestMatchers("/authenticate", "/register").permitAll()  // Public endpoints
//                                 .anyRequest().authenticated() // All other requests require authentication
//                 )
//                 .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before the authentication filter

//         return http.build();
//     }
// }
