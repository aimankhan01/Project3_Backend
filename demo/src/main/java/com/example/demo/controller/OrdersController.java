package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get an order by ID
    @GetMapping("/order")
    public Optional<Order> getOrderById(@RequestParam(value = "orderID") Integer orderID) {
        return orderRepository.findById(orderID);
    }

    // Delete an order by ID
    @DeleteMapping("/orders/remove")
    public void deleteOrder(@RequestParam(value = "orderID") Integer orderID) {
        orderRepository.deleteById(orderID);
    }

    // Search for orders by customer name
    @GetMapping("/orders/search")
    public List<Order> searchOrdersByCustomerName(@RequestParam(value = "customerName") String customerName) {
        return orderRepository.findByCustomerNameContaining(customerName);
    }
}