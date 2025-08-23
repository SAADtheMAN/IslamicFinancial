package com.isfin.islamicfinancial;

import com.isfin.islamicfinancial.entities.User;
import com.isfin.islamicfinancial.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
          .allowedOrigins("http://localhost:4200")
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowedHeaders("*")
          .allowCredentials(true);
      }
    };
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  // CommandLineRunner to encode existing plain passwords
  @Bean
  CommandLineRunner encodePasswords(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      for (User user : userRepository.findAll()) {
        String plain = user.getPassword();
        if (!plain.startsWith("$2")) { // skip already encoded
          user.setPassword(passwordEncoder.encode(plain));
          userRepository.save(user);
        }
      }
      System.out.println("All plain passwords are now encoded!");
    };
  }
}
