package com.jdong.studycafe.orders.dto;

import com.jdong.studycafe.orders.domain.Order;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;

    private Long cafeId;
    private String cafeName;

    private Long beverageId;
    private String beverageName;
    private String mainImageUrl;

    private Long chargedTime;
    private Long cost;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    @QueryProjection
    public OrderDTO(Long orderId, Long cafeId, String cafeName, Long beverageId, String beverageName, String mainImageUrl, Long chargedTime, Long cost, LocalDateTime createTime, LocalDateTime modifiedTime) {
        this.orderId = orderId;
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.mainImageUrl = mainImageUrl;
        this.chargedTime = chargedTime;
        this.cost = cost;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

}
