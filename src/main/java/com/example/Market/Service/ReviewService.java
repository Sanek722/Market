package com.example.Market.Service;
import com.example.Market.DTO.ProductReviewRequest;
import com.example.Market.DTO.ProductReviewResponse;
import com.example.Market.DTO.ProductReviewResponseWrapper;
import com.example.Market.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final RestTemplate restTemplate;

    public ProductReviewResponse processReviews(ProductReviewRequest request, Long productId) {
        String summarizerUrl = "http://localhost:5000/summarize";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductReviewRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ProductReviewResponseWrapper> response = restTemplate
                .postForEntity(summarizerUrl, entity, ProductReviewResponseWrapper.class);
        int id = productId.intValue();
        List<ProductReviewResponse> list_prod = response.getBody().getProducts();
        return list_prod.get(0);
    }
}
