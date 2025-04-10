package com.example.Market.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ClusterSummaryDTO //кластер с отзывами
{
    private String aspect;
    private String summary;
    private int count;
    private List<Long> review_ids;
}
