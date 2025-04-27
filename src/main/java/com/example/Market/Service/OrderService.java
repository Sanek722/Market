package com.example.Market.Service;

import com.example.Market.Model.Basket;
import com.example.Market.Model.Order;
import com.example.Market.Model.OrderItem;
import com.example.Market.Model.Product;
import com.example.Market.Repositories.BasketRepository;
import com.example.Market.Repositories.OrderRepository;
import com.example.Market.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(String username) {
        List<Basket> basketItems = basketRepository.findByUsername(username);
        if (basketItems.isEmpty()) {
            throw new RuntimeException("Корзина пуста");
        }

        Order order = new Order();
        order.setUsername(username);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();
        for (Basket basketItem : basketItems) {
            Product product = basketItem.getProduct();

            // проверка наличия товара
            if (product.getQuan() < basketItem.getQuantity()) {
                throw new RuntimeException("Недостаточно товара: " + product.getName());
            }

            product.setQuan(product.getQuan() - basketItem.getQuantity()); // уменьшаем наличие
            productRepository.save(product);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(basketItem.getQuantity());
            item.setPrice(product.getPrice()); // цена на момент покупки

            orderItems.add(item);
        }

        order.setItems(orderItems);
        basketRepository.deleteAll(basketItems); // очищаем корзину
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String username) {
        return orderRepository.findByUsername(username);
    }
}

