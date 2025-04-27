package com.example.Market.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Пустой конструктор для Hibernate
    public Basket() {
    }

    // Конструктор для создания корзины вручную
    public Basket(String username, Product product, int quantity) {
        this.username = username;
        this.product = product;
        this.quantity = quantity;
    }
}

