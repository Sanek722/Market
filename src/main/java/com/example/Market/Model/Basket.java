package com.example.Market.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // чей товар в корзине
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
