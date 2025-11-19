package org.example.Controller;

import org.example.Dto.ReviewCondition;
import org.example.Dto.ReviewRequest;
import org.example.Dto.ReviewResponse;
import org.example.Global.response.ApiResponse;
import org.example.Global.response.code.GeneralSuccessCode;
import org.example.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("umc9th/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    @GetMapping("/me")
    public ApiResponse<Page<ReviewResponse>> getMyReviews(@RequestParam("memberId") Long memberId, ReviewCondition condition, Pageable pageable) {
        Page<ReviewResponse> data = reviewService.findMyReviews(memberId, condition, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }

    @PostMapping("/store/{storeId}")
    public ApiResponse<ReviewResponse> addReview(@PathVariable("storeId") Long storeId, @RequestParam("memberId") Long memberId, @RequestBody ReviewRequest request) {
        ReviewResponse data = reviewService.addReview(storeId, memberId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }

}
