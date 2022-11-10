package com.jdong.studycafe.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Long beverageId;
    private Long cafeId;
    private Long chargedTime;
    private Long cost;
}
