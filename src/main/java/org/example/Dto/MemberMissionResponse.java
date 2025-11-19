package org.example.Dto;

import org.example.Entity.State;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberMissionResponse {

    private Long memberMissionId;
    private Long memberId;
    private Integer missionId;
    private State state;
    private LocalDateTime updatedAt;
}
