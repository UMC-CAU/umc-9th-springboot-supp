package org.example.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionSimpleResponse {

    private Integer missionId;
    private String title;
    private String description;
}
