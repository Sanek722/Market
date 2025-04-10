package com.example.Market.Model;
import jakarta.persistence.*;
@Entity
public class FinalSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5000)
    private String summary;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

