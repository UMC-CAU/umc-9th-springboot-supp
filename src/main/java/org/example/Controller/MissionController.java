package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MemberMissionResponse;
import org.example.Dto.MissionChallengeRequest;
import org.example.Dto.MissionSimpleResponse;
import org.example.Service.MemberMissionService;
import org.example.Service.MissionService;
import org.example.Global.response.ApiResponse;
import org.example.Global.response.code.GeneralSuccessCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MemberMissionService memberMissionService;
    private final MissionService missionService;

    @GetMapping("/stores/{storeId}")
    public ApiResponse<Page<MissionSimpleResponse>> getStoreMissions(
            @PathVariable Long storeId,
            Pageable pageable
    ) {
        Page<MissionSimpleResponse> data = missionService.getMissionsByStore(storeId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponse> challengeMission(@RequestBody MissionChallengeRequest request) {

        MemberMissionResponse data = memberMissionService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, data);
    }
}
