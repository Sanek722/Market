package com.example.Market.DTO.CommentDTO;

import lombok.Data;

@Data
public class CommentRequest {
    private Long productId;
    private String content;
}

