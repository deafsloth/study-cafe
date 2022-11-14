package com.jdong.studycafe.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotBlank(message = "beverageId 값은 비어었을 수 없습니다.")
    @Min(value=1,message = "beverageId 값은 1이상이어야 합니다.")
    private Long beverageId;

    @NotBlank(message = "CafeId 값은 비어었을 수 없습니다.")
    @Min(value=1,message = "CafeId 값은 1이상이어야 합니다.")
    private Long cafeId;

    @NotBlank(message = "chargedTime 값은 비어었을 수 없습니다.")
    @Min(value=1,message = "chargedTime 값은 1이상이어야 합니다.")
    private Long chargedTime;

    @NotBlank(message = "cost 값은 비어었을 수 없습니다.")
    @Min(value=1,message = "cost 값은 1이상이어야 합니다.")
    private Long cost;
}
