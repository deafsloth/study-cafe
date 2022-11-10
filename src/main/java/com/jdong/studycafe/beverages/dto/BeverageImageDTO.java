package com.jdong.studycafe.beverages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageImageDTO {

    private Long beverageImageId;
    private String imageUrl;
    private Long beverageId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
