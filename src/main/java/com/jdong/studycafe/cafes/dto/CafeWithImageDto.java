package com.jdong.studycafe.cafes.dto;

import com.jdong.studycafe.common.enumerate.Region;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CafeWithImageDto {

    private Long id;
    private String name;
    private String description;
    private Point location;
    private boolean isPreminum;
    private Region region;

    private List<String> imageUrl;
}
