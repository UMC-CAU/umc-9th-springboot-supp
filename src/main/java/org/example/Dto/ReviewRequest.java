package org.example.Dto;

import lombok.Getter;

@Getter
public class ReviewRequest {
    private Long store_id;
    private double rating;
    private String comment;
}
