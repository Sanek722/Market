package com.example.Market.Service;
import com.example.Market.DTO.ClusterSummaryDTO;
import com.example.Market.DTO.ProductReviewRequest;
import com.example.Market.DTO.ProductReviewResponse;
import com.example.Market.DTO.ProductReviewResponseWrapper;
import com.example.Market.Model.FinalSummary;
import com.example.Market.Model.Product;
import com.example.Market.Model.ReviewSummary;
import com.example.Market.Repositories.FinalSummaryRepository;
import com.example.Market.Repositories.ProductRepository;
import com.example.Market.Repositories.ReviewSummaryRepository;
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
    private final ProductRepository productRepository;

    private final FinalSummaryRepository finalSummaryRepository;
    private final ReviewSummaryRepository reviewSummaryRepository;
    public ProductReviewResponse processReviews(ProductReviewRequest request, Long productId) {
        String summarizerUrl = "http://localhost:5000/summarize";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductReviewRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ProductReviewResponseWrapper> response = restTemplate
                .postForEntity(summarizerUrl, entity, ProductReviewResponseWrapper.class);

        Product product = productRepository.findById(productId).orElseThrow();

        ProductReviewResponse data = response.getBody().getProducts().get(0);

        // Сохранение финальной выжимки
        FinalSummary finalSummary = new FinalSummary();
        finalSummary.setProduct(product);
        finalSummary.setSummary(data.getGenerated_summaries().getFinal_summary());
        finalSummary.setUsefulness(0);
        finalSummary = finalSummaryRepository.save(finalSummary);

        // Сохранение кластерных выжимок
        for (ClusterSummaryDTO cluster : data.getGenerated_summaries().getCluster_summaries()) {
            ReviewSummary reviewSummary = new ReviewSummary();
            reviewSummary.setFinalSummary(finalSummary);
            reviewSummary.setSummary(cluster.getSummary());
            reviewSummary.setAspect(cluster.getAspect());
            reviewSummary.setCount(cluster.getCount());
            reviewSummary.setReviewIds(cluster.getReview_ids());

            reviewSummaryRepository.save(reviewSummary);
        }

        return data;
    }

}
