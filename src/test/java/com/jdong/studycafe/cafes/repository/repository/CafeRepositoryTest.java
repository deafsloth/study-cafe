package com.jdong.studycafe.cafes.repository.repository;

import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import com.jdong.studycafe.common.enumerate.Region;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class CafeRepositoryTest {

    @Autowired
    private CafeRepository cafeRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Double longitude = 127.123+(i*0.01);
            Double latitude = 32.123+(i*0.01);
            String pointWKT = String.format("POINT(%s %s)", longitude, latitude);
            Point point = null;
            try {
                point = (Point) new WKTReader().read(pointWKT);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Cafe cafe = Cafe.builder()
                    .id(i)
                    .name("cafe"+i)
                    .description("description cafe "+i)
                    .location(point)
                    .isPremium(false)
                    .region(Region.SEOUL)
                    .build();
            System.out.println("cafe = " + cafe);
            cafeRepository.save(cafe);

        });
    }

    @Test
    public void 카페_생성() {
        Double longitude = 127.123+(0.01);
        Double latitude = 32.123+(0.01);
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        Point point = null;
        try {
            point = (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Cafe cafeAAA = Cafe.builder()
                .id(1)
                .name("cafeAAA")
                .description("description cafe AAA")
                .location(point)
                .isPremium(true)
                .region(Region.SEOUL)
                .build();
        System.out.println("cafe = " + cafeAAA);
        cafeRepository.save(cafeAAA);

        longitude = 127.123+(0.1);
        latitude = 32.123+(0.1);
        pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        point = null;
        try {
            point = (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Cafe cafeBBB = Cafe.builder()
                .id(2)
                .name("cafeBBB")
                .description("description cafe BBB")
                .location(point)
                .isPremium(false)
                .region(Region.GYEONGGI)
                .build();
        System.out.println("cafe = " + cafeBBB);
        cafeRepository.save(cafeBBB);
    }
    @Test
    public void testFindNearWithinDistance() {
        Double longitude = 127.123;
        Double latitude = 32.123;
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        Point point = null;
        try {
            point = (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("point = " + point);

        List<Cafe> nearWithinDistance = cafeRepository.findNearWithinDistance(point, 10000);
        System.out.println("nearWithinDistance = " + nearWithinDistance);
    }

    @Test
    public void testPagenate() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
        Page<Cafe> result = cafeRepository.findAll(pageable);
    }
}