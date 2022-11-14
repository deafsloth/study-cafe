package com.jdong.studycafe.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequestDTO {

    @NotNull(message = "chargeCredit 값은 비어었을 수 없습니다.")
    @Min(value=1,message = "chargeCredit 값은 1이상이어야 합니다.")
    private Long chargeCredit;

}
