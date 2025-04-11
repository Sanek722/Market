package com.example.Market.Controller;

import com.example.Market.DTO.ProductReviewRequest;
import com.example.Market.DTO.ProductReviewResponse;
import com.example.Market.Service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/market")
public class ReviewController {

    private final ReviewService processingService;

    public ReviewController(ReviewService processingService) {
        this.processingService = processingService;
    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping("/summarize")
    public Mono<ProductReviewResponse> summarize(@RequestBody ProductReviewRequest request)
    {
        return processingService.processReviews(request);
    }
}

