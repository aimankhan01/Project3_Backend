package com.example.demo.controller;

import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082"})
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all Orders
    @GetMapping("/all")
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get a single Order by ID
    @GetMapping("/single")
    public ResponseEntity<Orders> getOrderById(@RequestParam(value = "orderID") Integer orderID) {
        Optional<Orders> order = orderRepository.findById(orderID);
        return order.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all Orders by user ID
    @GetMapping("/orderById")
    public List<Orders> getOrdersByUserId(@RequestParam(value = "userID") Integer userID) {
        return orderRepository.findOrderByUserID(userID);
    }

    // Create a new Order
@PostMapping("/create")
public ResponseEntity<Orders> createOrder(@RequestParam(value = "userID") Integer userID,
                                          @RequestBody OrderRequest orderRequest) { // OrderRequest should be a new class
    Orders newOrder = new Orders();
    newOrder.setUserID(userID);
    newOrder.setCartItems(orderRequest.getCartItems());
    newOrder.setTotal(orderRequest.getTotal());

    // Save the new Order to the repository
    Orders savedOrder = orderRepository.save(newOrder);

    // Return the saved Order as JSON response
    return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
}

    // Update an existing Order
    @PatchMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestParam(value = "orderID") Integer orderID,
                                              @RequestBody Orders updatedOrder) {
        Optional<Orders> order = orderRepository.findById(orderID);
        if (order.isPresent()) {
            Orders existingOrder = order.get();
            existingOrder.setCartItems(updatedOrder.getCartItems());
            existingOrder.setTotal(updatedOrder.getTotal());
            orderRepository.save(existingOrder);
            return new ResponseEntity<>("Order updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found!", HttpStatus.NOT_FOUND);
        }
    }

    // Delete an Order by ID
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestParam(value = "orderID") Integer orderID) {
        if (orderRepository.existsById(orderID)) {
            orderRepository.deleteById(orderID);
            return new ResponseEntity<>("Order deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found!", HttpStatus.NOT_FOUND);
        }
    }
}