package Repository;

import Dto.ReviewCondition;
import Dto.ReviewRequest;
import Dto.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<ReviewResponse> findMyReviews(Long memberId, ReviewCondition condition, Pageable pageable);
}
