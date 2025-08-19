package com.isfin.islamicfinancial.controllers;

import com.isfin.islamicfinancial.dto.AuthResponse;
import com.isfin.islamicfinancial.dto.LoginRequest;
import com.isfin.islamicfinancial.dto.RegisterRequest;
import com.isfin.islamicfinancial.entities.User;
import com.isfin.islamicfinancial.security.JwtService;
import com.isfin.islamicfinancial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authManager;
  private final JwtService jwtService;
  private final UserService userService;

  @Autowired
  public AuthController(AuthenticationManager authManager, JwtService jwtService, UserService userService) {
    this.authManager = authManager;
    this.jwtService = jwtService;
    this.userService = userService;
  }

  // Login endpoint
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
      authManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );

      var userDetails = userService.loadUserByUsername(request.getUsername());
      var token = jwtService.generateToken(userDetails);

      return ResponseEntity.ok(new AuthResponse(token));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
  }

  // Registration endpoint
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    if (userService.existsByUsername(request.getUsername())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
    }

    User newUser = new User();
    newUser.setUsername(request.getUsername());
    newUser.setPassword(request.getPassword()); // Make sure to hash passwords in production!
    newUser.setEmail(request.getEmail());

    userService.saveUser(newUser);
    return ResponseEntity.ok("User registered successfully");
  }
}
