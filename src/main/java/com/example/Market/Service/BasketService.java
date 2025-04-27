package com.example.Market.Service;

import com.example.Market.Model.Basket;
import com.example.Market.Model.Product;
import com.example.Market.Repositories.BasketRepository;
import com.example.Market.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Basket> getBasketByUsername(String username) {
        return basketRepository.findByUsername(username);
    }

    public void addToBasket(String username, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Basket basket = basketRepository.findByUsernameAndProductId(username, productId)
                .orElse(new Basket(username, product, 0));

        basket.setQuantity(basket.getQuantity() + 1);
        basketRepository.save(basket);
    }

    public void removeFromBasket(String username, Long productId) {
        basketRepository.findByUsernameAndProductId(username, productId)
                .ifPresent(basketRepository::delete);
    }

    public void updateQuantity(String username, Long productId, int quantity) {
        Basket basket = basketRepository.findByUsernameAndProductId(username, productId)
                .orElseThrow();
        basket.setQuantity(quantity);
        basketRepository.save(basket);
    }
}
