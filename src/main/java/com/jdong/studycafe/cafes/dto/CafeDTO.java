package com.jdong.studycafe.cafes.dto;

import com.jdong.studycafe.common.enumerate.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeDTO {
    private Long id;
    private String name;
    private String description;

//    private Point location;
    private Double latitude;
    private Double longitude;

    private boolean isPremium;
    private Region region;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
