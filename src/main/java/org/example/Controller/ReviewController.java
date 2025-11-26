package org.example.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.Dto.ReviewCondition;
import org.example.Dto.ReviewRequest;
import org.example.Dto.ReviewResponse;
import org.example.Global.Page.ValidPageable;
import org.example.Global.response.ApiResponse;
import org.example.Global.response.code.GeneralSuccessCode;
import org.example.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/umc9th/api/reviews")   // 슬래시만 하나 추가
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/me")
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "내가 작성한 리뷰를 10개씩 조회합니다. "
    )
    public ApiResponse<Page<ReviewResponse>> getMyReviews(
            @Parameter(description = "회원 ID", required = true, example = "1")
            @RequestParam("memberId") Long memberId,
            ReviewCondition condition,
            @Parameter(description = "조회할 페이지 번호", example = "1")
            @ValidPageable Pageable pageable
    ) {
        Page<ReviewResponse> data = reviewService.findMyReviews(memberId, condition, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }

    @PostMapping("/store/{storeId}")
    @Operation(
            summary = "가게 리뷰 등록",
            description = "회원이 리뷰를 등록합니다."
    )
    public ApiResponse<ReviewResponse> addReview(
            @Parameter(description = "가게 ID", required = true, example = "1")
            @PathVariable("storeId") Long storeId,
            @Parameter(description = "회원 ID", required = true, example = "1")
            @RequestParam("memberId") Long memberId,
            @RequestBody ReviewRequest request
    ) {
        ReviewResponse data = reviewService.addReview(storeId, memberId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }
}
