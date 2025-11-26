package org.example.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.Dto.MemberMissionResponse;
import org.example.Dto.MissionChallengeRequest;
import org.example.Dto.MissionSimpleResponse;
import org.example.Global.Page.ValidPageable;
import org.example.Global.response.ApiResponse;
import org.example.Global.response.code.GeneralSuccessCode;
import org.example.Service.MemberMissionService;
import org.example.Service.MissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/umc9th/api/missions")   // 프로젝트 컨벤션 맞추고 싶으면 이렇게
public class MissionController {

    private final MemberMissionService memberMissionService;
    private final MissionService missionService;

    @GetMapping("/stores/{storeId}")
    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "storeId에 해당하는 가게의 미션들을 10개씩 페이징 조회합니다. " +
                    "page 쿼리 파라미터는 1 이상이어야 합니다."
    )
    public ApiResponse<Page<MissionSimpleResponse>> getStoreMissions(
            @Parameter(description = "가게 ID", example = "1", required = true)
            @PathVariable Long storeId,
            @Parameter(description = "조회할 페이지 번호(1 이상)", example = "1")
            @ValidPageable Pageable pageable     // ✅ 여기만 바뀐 핵심
    ) {
        Page<MissionSimpleResponse> data = missionService.getMissionsByStore(storeId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }

    @PostMapping("/challenge")
    @Operation(
            summary = "미션 도전",
            description = "회원이 특정 미션에 도전(진행 시작)합니다."
    )
    public ApiResponse<MemberMissionResponse> challengeMission(
            @RequestBody MissionChallengeRequest request
    ) {
        MemberMissionResponse data = memberMissionService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }

    // ③ 내가 진행중인 미션 목록 조회
    @GetMapping("/me/in-progress")
    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "진행중 상태의 미션들을 10개씩 조회합니다. "
    )
    public ApiResponse<Page<MemberMissionResponse>> getMyInProgressMissions(
            @Parameter(description = "회원 ID", example = "1", required = true)
            @RequestParam("memberId") Long memberId,
            @Parameter(description = "조회할 페이지 번호", example = "1")
            @ValidPageable Pageable pageable
    ) {
        Page<MemberMissionResponse> data =
                memberMissionService.getMyInProgressMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }
}
