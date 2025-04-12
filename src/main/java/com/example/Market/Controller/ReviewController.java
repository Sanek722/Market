package com.example.Market.Controller;

import com.example.Market.DTO.ProductReviewRequest;
import com.example.Market.DTO.ProductReviewResponse;
import com.example.Market.DTO.ReviewDTO;
import com.example.Market.Model.Comment;
import com.example.Market.Model.Product;
import com.example.Market.Repositories.ProductRepository;
import com.example.Market.Service.CommentService;
import com.example.Market.Service.ProductService;
import com.example.Market.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class ReviewController {

    private final ReviewService reviewService;
    private final CommentService commentService;
    private final ProductService productService;
//    @PreAuthorize("hasRole('ROLE_Admin')")
//    @PostMapping("/summarize")
//    public ResponseEntity<List<ProductReviewResponse>> summarize(@RequestBody ProductReviewRequest request) {
//        List<ProductReviewResponse> responses = reviewService.processReviews(request);
//        return ResponseEntity.ok(responses);
//    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping("/summarize/{id}")
    public ResponseEntity<ProductReviewResponse> summarizeById(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        List<Comment> comments = commentService.getCommentsByProductId(productId);

        List<ReviewDTO> reviews = comments.stream()
                .map(c -> new ReviewDTO(c.getId(), c.getContent(), c.getUsefullness()))
                .toList();

        ProductReviewRequest request = new ProductReviewRequest();
        request.setName(product.getName());
        request.setReviews(reviews);

        return ResponseEntity.ok(reviewService.processReviews(request, productId));
    }



}

