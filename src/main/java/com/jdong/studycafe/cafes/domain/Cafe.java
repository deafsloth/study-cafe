package com.jdong.studycafe.cafes.domain;

import com.jdong.studycafe.common.domain.BaseTimeEntity;
import com.jdong.studycafe.common.enumerate.Region;
import lombok.*;

import javax.persistence.*;

//import org.springframework.data.geo.Point;
//import org.geolatte.geom.Geometry;
//import com.vividsolutions.jts.geom.Point;
import org.locationtech.jts.geom.Point;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@ToString
@Entity
public class Cafe extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private long id;

    private String name;
    private String description;
    private String mainImageUrl;

    //ST_Point(float x, float y); geodetic coordinates, X is longitude and Y is latitude
    @Column
    private Point location;

    private boolean isPremium;

    @Enumerated(EnumType.STRING)
    private Region region;

}
