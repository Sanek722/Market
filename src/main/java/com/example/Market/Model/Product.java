package com.example.Market.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    private String category;

    private int quan; //количество

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ReviewSummary> reviewSummaries;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private FinalSummary finalSummary;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
