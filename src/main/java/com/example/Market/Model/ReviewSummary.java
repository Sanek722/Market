package com.example.Market.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class ReviewSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aspect;
    @Column(length = 7000)
    private String summary;
    private int count;

    @ElementCollection
    private List<Long> reviewIds;

    @ManyToOne
    @JsonBackReference
    private FinalSummary finalSummary;
}

