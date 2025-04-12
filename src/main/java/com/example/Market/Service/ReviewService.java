package com.example.Market.Service;
import com.example.Market.DTO.ProductReviewRequest;
import com.example.Market.DTO.ProductReviewResponse;
import com.example.Market.DTO.ProductReviewResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ReviewService
{
    private final WebClient webClient = WebClient.create("http://localhost:5000"); // Python URL

    public Mono<ProductReviewResponse> processReviews(ProductReviewRequest request) {
        return webClient.post()
                .uri("/summarize")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ProductReviewResponseWrapper.class)
                .map(wrapper -> wrapper.getProducts().get(0)); // Только первый продукт
    }
}
