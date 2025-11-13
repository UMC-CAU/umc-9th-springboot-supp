package Service;

import Dto.ReviewCondition;
import Dto.ReviewResponse;
import Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    public Page<ReviewResponse> findMyReviews(Long memberId, ReviewCondition condition, Pageable pageable) {
        if (condition == null) {
            condition = new ReviewCondition();
        }

        return reviewRepository.findMyReviews(memberId, condition, pageable);
    }
}
