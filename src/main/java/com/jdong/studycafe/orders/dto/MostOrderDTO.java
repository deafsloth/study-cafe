package com.jdong.studycafe.orders.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class MostOrderDTO {

    private Long cafeId;
    private String cafeName;

    private Long beverageId;
    private String beverageName;
    private String mainImageUrl;
    private Long cnt;

    @QueryProjection
    public MostOrderDTO(Long cafeId, String cafeName, Long beverageId, String beverageName, String mainImageUrl, Long cnt) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.mainImageUrl = mainImageUrl;
        this.cnt = cnt;
    }
}
