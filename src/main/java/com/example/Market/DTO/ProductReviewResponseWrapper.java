package com.example.Market.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ProductReviewResponseWrapper {
    @JsonProperty("products")
    private List<ProductReviewResponse> products;
}

