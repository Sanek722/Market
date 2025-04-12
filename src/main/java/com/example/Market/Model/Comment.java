package com.example.Market.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String content;
    private Integer usefullness;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name= "product_id")
    @JsonBackReference
    private Product product;
}
