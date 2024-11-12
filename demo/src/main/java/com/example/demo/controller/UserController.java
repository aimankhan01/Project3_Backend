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
}