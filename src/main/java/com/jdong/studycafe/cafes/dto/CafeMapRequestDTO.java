package com.jdong.studycafe.cafes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeMapRequestDTO {

    @NotNull(message = "longitude 값은 비어었을 수 없습니다.")
    @DecimalMin(value = "-180", message = "longitude 값은 -180 이하일 수 없습니다.")
    @DecimalMax(value = "180", message = "longitude 값은 180 이상일 수 없습니다.")
    String longitude;

    @NotNull(message = "latitude 값은 비어었을 수 없습니다.")
    @DecimalMin(value = "-90", message = "latitude 값은 -90 이하일 수 없습니다.")
    @DecimalMax(value = "90", message = "latitude 값은 90 이상일 수 없습니다.")
    String latitude;

    @NotNull(message = "radius 값은 비어었을 수 없습니다.")
    @DecimalMin(value = "0.0",message = "radius 값은 0 이상의 수 입니다..")
    Double radius;
}
