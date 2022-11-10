package com.jdong.studycafe.cafes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeMapRequestDTO {
    Double longitude;
    Double latitude;
    Double radius;
}
