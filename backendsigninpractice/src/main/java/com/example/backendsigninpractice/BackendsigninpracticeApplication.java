package com.example.backendsigninpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BackendsigninpracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendsigninpracticeApplication.class, args);
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//   @Bean
  //   public CorsFilter corsFilter() {
  //       CorsConfiguration corsConfig = new CorsConfiguration();
  //       corsConfig.setAllowCredentials(true);
  //       corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Allow frontend
  //       corsConfig.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
  //       corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

  //       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  //       source.registerCorsConfiguration("/**", corsConfig);

  //       return new CorsFilter();
	// }
}
