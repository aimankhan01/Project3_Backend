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
public class OrderController {

    private final OrderRepository OrderRepository;

    @Autowired
    public OrderController(OrderRepository OrderRepository) {
        this.OrderRepository = OrderRepository;
    }

      // Get all Orders
      @GetMapping("/all")
      public List<Orders> getAllOrders() {
          return OrderRepository.findAll();
      }
  
      // Get a single Order by ID
      @GetMapping("/single")
      public ResponseEntity<Orders> getOrderById(@RequestParam(value = "orderID") Integer OrderID) {
          Optional<Orders> Order = OrderRepository.findById(OrderID);
          return Order.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
      }
  
      // Get all Orders by user ID
      @GetMapping("/user")
      public List<Orders> getOrdersByUserId(@RequestParam(value = "userID") Integer userID) {
          return OrderRepository.findOrderByUserID(userID);
      }
  
      // Create a new Order
      @PostMapping("/create")
      public ResponseEntity<Orders> createOrder(@RequestParam(value = "userID") Integer userID,
                                                      @RequestParam(value = "name") String OrderName) {
          Orders newOrder = new Orders();
          newOrder.setUserID(userID);
          newOrder.setName(OrderName);
  
          // Save the new Order to the repository
          Orders savedOrder = OrderRepository.save(newOrder);
  
          // Return the saved Order as JSON response
          return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
      }
  
  
      // Update an existing Order
      @PatchMapping("/update")
      public ResponseEntity<String> updateOrder(@RequestParam(value = "orderID") Integer OrderID,
                                                   @RequestParam(value = "name", required = false) String OrderName) {
          Optional<Orders> Order = OrderRepository.findById(OrderID);
          if (Order.isPresent()) {
              if (OrderName != null) {
                  Order.get().setName(OrderName);
              }
              OrderRepository.save(Order.get());
              return new ResponseEntity<>("Order updated successfully!", HttpStatus.OK);
          } else {
              return new ResponseEntity<>("Order not found!", HttpStatus.NOT_FOUND);
          }
      }
  
      // Delete a Order by ID
      @DeleteMapping("/delete")
      public ResponseEntity<String> deleteOrder(@RequestParam(value = "OrderID") Integer OrderID) {
          if (OrderRepository.existsById(OrderID)) {
              OrderRepository.deleteById(OrderID);
              return new ResponseEntity<>("Order deleted successfully!", HttpStatus.OK);
          } else {
              return new ResponseEntity<>("Order not found!", HttpStatus.NOT_FOUND);
          }
      }
}