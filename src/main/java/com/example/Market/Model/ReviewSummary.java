package com.example.Market.Model;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ReviewSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aspect;

    @Column(length = 7000)
    private String summary;

    @ElementCollection
    private List<Long> reviewIds;

    private int count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

