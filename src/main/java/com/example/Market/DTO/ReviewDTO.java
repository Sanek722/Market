package com.example.Market.DTO;

import lombok.Data;

@Data
public class ReviewDTO //Отзыв
{
    private Long id;
    private String text;
    private Integer usefulness;
}
