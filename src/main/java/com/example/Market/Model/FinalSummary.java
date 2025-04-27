package com.example.Market.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class FinalSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 7000)
    private String summary;
    private Integer usefulness = 0;
    @ManyToOne
    @JsonBackReference
    private Product product;
    @OneToMany(mappedBy = "finalSummary", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ReviewSummary> clusterSummaries;
}

