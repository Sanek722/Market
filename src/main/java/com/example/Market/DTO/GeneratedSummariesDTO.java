package com.example.Market.DTO;

import lombok.Data;

import java.util.List;

@Data
public class GeneratedSummariesDTO //кластеры с итоговой выжимкой
{
    private List<ClusterSummaryDTO> cluster_summaries;
    private String final_summary;
}
