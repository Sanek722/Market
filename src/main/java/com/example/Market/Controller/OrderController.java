package com.example.Market.Controller;

import com.example.Market.Model.Order;
import com.example.Market.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestHeader("X-User") String username) {
        Order order = orderService.createOrder(username);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUserOrders(@RequestHeader("X-User") String username) {
        return ResponseEntity.ok(orderService.getUserOrders(username));
    }
}
