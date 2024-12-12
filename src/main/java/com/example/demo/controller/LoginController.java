package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082"})




try {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                String jsonResponse = String.format(
                    "{\"userID\":\"%s\",\"name\":\"%s\",\"email\":\"%s\",\"password\":\"%s\",\"role\":\"%s\"}",
                    user.getUserID(), user.getName(), user.getEmail(), user.getPassword(), user.getAdmin()
                );
                return ResponseEntity.ok(jsonResponse);
            } else {
                System.err.println("Invalid password for email: " + email);
                return ResponseEntity.status(401).body("Invalid password.");
            }
        } else {
            System.err.println("User not found for email: " + email);
            return ResponseEntity.status(404).body("User not found.");
        }
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(500).body("An unexpected error occurred.");
    }



   @PostMapping("/admin")
   public ResponseEntity<Map<String, String>> adminLogin(@RequestBody Map<String, String> loginData) {
    String email = loginData.get("email");
    String password = loginData.get("password");

    Map<String, String> response = new HashMap<>();

    if (email == null || password == null) {
        response.put("error", "Email or password is missing.");
        return ResponseEntity.badRequest().body(response);
    }

    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        
        if (!user.getAdmin()) {
            response.put("error", "Access denied. Admin privileges required.");
            return ResponseEntity.status(403).body(response);
        }
        
        if (user.getPassword().equals((password))) {
            response.put("message", "Welcome Admin " + user.getName() + "!");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Invalid password.");
            return ResponseEntity.status(401).body(response);
        }
    } else {
        response.put("error", "Admin user not found.");
        return ResponseEntity.status(404).body(response);
    }
  }

  @RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Login a user
   @PostMapping()
public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
    String email = loginData.get("email");
    String password = loginData.get("password");

    // Add logging
    System.out.println("Login attempt with email: " + email);

    if (email == null || password == null) {
        System.err.println("Email or password is missing.");
        return ResponseEntity.badRequest().body("Email or password is missing.");
    }

    
}