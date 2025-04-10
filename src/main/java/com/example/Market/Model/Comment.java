package com.example.Market.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String content;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
}
