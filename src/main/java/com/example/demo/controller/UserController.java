package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/test")
public ResponseEntity<String> test() {
    return ResponseEntity.ok("Hello, world!");
}

    // Get a user by ID
    @GetMapping("/user")
    public Optional<User> getUserById(@RequestParam(value = "userID") Integer userID) {
        return userRepository.findById(userID);
    }
      // Delete a user by ID
      @DeleteMapping("/users/remove")
      public void deleteUser(@RequestParam(value = "userID") Integer userID) {
          userRepository.deleteById(userID);
      }
  
      // Search for users by name 
      @GetMapping("/users/search")
      public List<User> searchUsersByName(@RequestParam(value = "name") String name) {
          return userRepository.findByNameContaining(name);
      }
      @PostMapping("/signup")
      public ResponseEntity<?> signupUser(@RequestBody Map<String, String> signupData) {
          String name = signupData.get("name");
          String email = signupData.get("email");
          String password = signupData.get("password");
          String salt = signupData.get("salt");
  
          if (email == null || password == null || salt == null) {
              return ResponseEntity.badRequest().body("Missing email, password, or salt.");
          }
  
          User newUser = new User();
          newUser.setName(name);
          newUser.setEmail(email);
          newUser.setPassword(password);
          newUser.setAdmin(false);
          newUser.setSalt(salt); // Store salt with user record
          userRepository.save(newUser);
          userRepository.save(newUser);
  
          return ResponseEntity.ok("User created successfully.");
      }

      @PostMapping("/testUser")
      public void testUser() {
        User newUser = new User();
        newUser.setName("xedi");
        newUser.setEmail("b@gmail.com");
        newUser.setPassword("12345");
        newUser.setAdmin(false);
        userRepository.save(newUser);
      }
  
      // Update an existing user by ID
      @PutMapping("/users/update")
      public ResponseEntity<String> updateUser(@RequestParam("userID") Integer userID, @RequestBody Map<String, Object> updatedDetails) {
  
          Optional<User> optionalUser = userRepository.findById(userID);
          if (optionalUser.isPresent()) {
              User user = optionalUser.get();
  
              // Update fields only if present in the request body
              if (updatedDetails.containsKey("name")) {
                  user.setName((String) updatedDetails.get("name"));
              }
              if (updatedDetails.containsKey("password")) {
                  user.setPassword((String) updatedDetails.get("password"));
                  user.setSalt((String) updatedDetails.get("salt"));
              }
  
              userRepository.save(user);
              return ResponseEntity.ok("User updated successfully");
          } else {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
          }
      }

     
  
}