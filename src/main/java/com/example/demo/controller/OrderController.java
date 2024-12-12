package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
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
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDTO orderDTO) {
        Orders newOrder = new Orders();
        newOrder.setUserID(orderDTO.getUserID());
        newOrder.setCartItems(orderDTO.getCartItems());
        newOrder.setTotal(orderDTO.getTotal());

        // Save the new Order to the repository
        Orders savedOrder = orderRepository.save(newOrder);

        // Return the saved Order as JSON response
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Update an existing Order
    @PatchMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestParam(value = "orderID") Integer orderID,
                                              @RequestParam(value = "name", required = false) String orderName) {
        Optional<Orders> order = orderRepository.findById(orderID);
        if (order.isPresent()) {
            // If `setName` method does not exist or isn't needed, just save the order without modifications
            orderRepository.save(order.get());
            return new ResponseEntity<>("Order updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found!", HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Order by ID
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