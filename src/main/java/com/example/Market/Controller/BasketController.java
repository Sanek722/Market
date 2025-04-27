package com.example.Market.Controller;

import com.example.Market.Model.Basket;
import com.example.Market.Service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @GetMapping("/all")
    public ResponseEntity<?> getBasket(@RequestHeader("X-User") String username) {
        return ResponseEntity.ok(basketService.getBasketByUsername(username));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addToBasket(@RequestHeader("X-User") String username,
                                         @PathVariable Long productId) {
        basketService.addToBasket(username, productId);
        return ResponseEntity.ok("Product added");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeFromBasket(@RequestHeader("X-User") String username,
                                              @PathVariable Long productId) {
        basketService.removeFromBasket(username, productId);
        return ResponseEntity.ok("Product removed");
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateQuantity(@RequestHeader("X-User") String username,
                                            @PathVariable Long productId,
                                            @RequestParam int quantity) {
        basketService.updateQuantity(username, productId, quantity);
        return ResponseEntity.ok("Quantity updated");
    }
}


