package com.jdong.studycafe.service;

import com.jdong.studycafe.cafes.service.CafeService;
import com.jdong.studycafe.common.enumerate.Region;
import com.jdong.studycafe.cafes.dto.CafeDTO;
import com.jdong.studycafe.cafes.dto.CafeMapRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class CafeServiceImplTest {
    @Autowired
    private CafeService cafeService;

    @Test
    public void 점을기준으로_반지름만큼_카페탐색() {
        CafeMapRequestDTO cafeMapRequest = CafeMapRequestDTO.builder()
                .longitude(127.123)
                .latitude(32.123)
                .radius(100000D).build();

        List<CafeDTO> cafeWithLocationList = cafeService.findCafeWithLocation(cafeMapRequest);
        cafeWithLocationList.stream()
                .map(cafeDTO -> cafeDTO.getName())
                .forEach(cafeName-> System.out.println("cafeName = " + cafeName));

    }

    @Test
    public void testRegistration() {
        System.out.println("cafeService.getClass().getName() = " + cafeService.getClass().getName());;

        Double longitude = 127.123;
        Double latitude = 32.123;
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        Point point = null;
        try {
            point = (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        CafeDTO cafeDTO = CafeDTO.builder()
                .name("cafeCCC")
                .description("description of CafeCCC")
                .longitude(longitude)
                .latitude(latitude)
                .isPremium(true)
                .region(Region.SEOUL)
                .build();
        Long cafeId = cafeService.registerCafe(cafeDTO);
        System.out.println("cafeId = " + cafeId);

    }

}