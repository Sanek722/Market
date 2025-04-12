package com.example.Market.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO //Отзыв
{
    private Long id;
    private String text;
    private Integer usefulness;


}
