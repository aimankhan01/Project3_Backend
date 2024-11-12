package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Method to find users by name (case-sensitive)
    @Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%', :name, '%')")
    List<User> findByNameContaining(@Param(value = "name") String name);

    // Finds a user by their email address (case-sensitive)
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
