package com.example.Market.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProductReviewRequest //отправка имени товара и списка отзывов
{
    private String name;
    private List<ReviewDTO> reviews;
}
