package org.example.Service;

import org.example.Dto.ReviewCondition;
import org.example.Dto.ReviewRequest;
import org.example.Dto.ReviewResponse;
import org.example.Repository.ReviewRepository;
import org.example.Entity.Member;
import org.example.Entity.Review;
import org.example.Entity.Store;
import org.example.Repository.StoreRepository;
import org.example.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public Page<ReviewResponse> findMyReviews(Long memberId, ReviewCondition condition, Pageable pageable) {
        if (condition == null) {
            condition = new ReviewCondition();
        }

        return reviewRepository.findMyReviews(memberId, condition, pageable);
    }

    public ReviewResponse addReview(Long storeId, Long memberId, ReviewRequest request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다. id=" + storeId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id=" + memberId));

        Review review = Review.builder()
                .store(store)
                .member(member)
                .rating(request.getRating())
                .content(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        Review saved = reviewRepository.save(review);

        return new ReviewResponse(
                saved.getReview_id(),
                saved.getRating(),
                saved.getContent(),
                saved.getCreatedAt()
        );
    }
}
