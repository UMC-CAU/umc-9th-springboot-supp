package org.example.Repository;

import org.example.Dto.ReviewCondition;
import org.example.Dto.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<ReviewResponse> findMyReviews(Long memberId, ReviewCondition condition, Pageable pageable);
}
