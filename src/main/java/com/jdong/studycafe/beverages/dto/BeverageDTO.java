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
public class BeverageDTO {
    private Long beverageId;
    private String name;
    private int price;
    private boolean isPremium;
    private String description;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
