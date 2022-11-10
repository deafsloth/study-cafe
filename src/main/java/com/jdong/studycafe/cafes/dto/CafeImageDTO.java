package com.jdong.studycafe.cafes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeImageDTO {

    private Long cafeImageId;
    private String imageUrl;
    private Long cafeId;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
