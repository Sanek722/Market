package com.example.Market;
import com.example.Market.DTO.*;
import com.example.Market.Model.Product;
import com.example.Market.Model.ReviewSummary;
import com.example.Market.Repositories.FinalSummaryRepository;
import com.example.Market.Repositories.ProductRepository;
import com.example.Market.Repositories.ReviewSummaryRepository;
import com.example.Market.Service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock private ProductRepository productRepository;
    @Mock private FinalSummaryRepository finalSummaryRepository;
    @Mock private ReviewSummaryRepository reviewSummaryRepository;
    @Mock private RestTemplate restTemplate;

    @InjectMocks private ReviewService reviewService;

    @Test
    void testProcessReviews_Success() {
        Long productId = 1L;
        Product mockProduct = new Product(productId, "Мышь", "desc", 1000, "tech", 5);

        ProductReviewRequest request = new ProductReviewRequest();
        ClusterSummaryDTO cluster = new ClusterSummaryDTO();
        cluster.setAspect("Качественный материал, беспроводная");
        cluster.setSummary("Быстрая мышь");
        cluster.setCount(3);
        cluster.setReview_ids(List.of(1L, 2L));

        ProductReviewResponse responseBody = new ProductReviewResponse();
        GeneratedSummariesDTO summaries = new GeneratedSummariesDTO();
        summaries.setFinal_summary("Отличный продукт с качественным материалом и беспроводным подключением");
        summaries.setCluster_summaries(List.of(cluster));
        responseBody.setGenerated_summaries(summaries);

        ProductReviewResponseWrapper wrapper = new ProductReviewResponseWrapper();
        wrapper.setProducts(List.of(responseBody));

        ResponseEntity<ProductReviewResponseWrapper> mockResponse = new ResponseEntity<>(wrapper, HttpStatus.OK);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.eq(ProductReviewResponseWrapper.class)))
                .thenReturn(mockResponse);
        Mockito.when(finalSummaryRepository.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));


        ProductReviewResponse result = reviewService.processReviews(request, productId);

        assertEquals("Отличный продукт с качественным материалом и беспроводным подключением", result.getGenerated_summaries().getFinal_summary());
        Mockito.verify(reviewSummaryRepository, Mockito.times(1)).save(Mockito.any());
    }
}


