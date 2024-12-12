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
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082"})
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
  
          if (email == null || password == null) {
              return ResponseEntity.badRequest().body("Missing email, password");
          }
  
          User newUser = new User();
          newUser.setName(name);
          newUser.setEmail(email);
          newUser.setPassword(password);
          newUser.setAdmin(false);
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
  
     @PutMapping("/users/update")
public ResponseEntity<String> updateUser(@RequestParam("userID") Integer userID, @RequestBody Map<String, Object> updatedDetails) {
    System.out.println("Updated details: " + updatedDetails); // Log the incoming data
    Optional<User> optionalUser = userRepository.findById(userID);
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        if (updatedDetails.containsKey("name")) {
            user.setName((String) updatedDetails.get("name"));
        }
        if (updatedDetails.containsKey("email")) {
            user.setEmail((String) updatedDetails.get("email"));
        }
        if (updatedDetails.containsKey("password")) {
            user.setPassword((String) updatedDetails.get("password"));
        }
        if (updatedDetails.containsKey("role")) {
            user.setAdmin(Boolean.parseBoolean((String) updatedDetails.get("role")));
        }
        userRepository.save(user); // Save the updated user
        return ResponseEntity.ok("User updated successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
     
  
}