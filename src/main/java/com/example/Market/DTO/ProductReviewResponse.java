package com.example.Market.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProductReviewResponse //получение ответа от модуля
{
    private String name;
    private List<ReviewDTO> reviews;
    private GeneratedSummariesDTO generated_summaries;
}
