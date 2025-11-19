package org.example.Dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponse {
    private Long reviewId;
    private Double rating;
    private String content;
    private LocalDateTime createdAt;

    @QueryProjection
    public ReviewResponse(Long reviewId, Double rating, String content, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
    }
}

