package com.jdong.studycafe.orders.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class StudyDTO {
    private Long studyId;

    private Long memberId;
    private Long cafeId;
    private String cafeName;

    private Long beverageId;
    private String beverageName;
    private Long chargedTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @QueryProjection
    public StudyDTO(Long studyId, Long memberId, Long cafeId, String cafeName, Long beverageId, String beverageName, Long chargedTime, LocalDateTime startTime, LocalDateTime endTime) {
        this.studyId = studyId;
        this.memberId = memberId;
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.chargedTime = chargedTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
