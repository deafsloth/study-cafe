package com.jdong.studycafe.cafes.repository.repository;

import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.cafes.domain.CafeImage;
import com.jdong.studycafe.cafes.repository.CafeImageRepository;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class CafeImageRepositoryTest {
    @Autowired
    private CafeImageRepository cafeImageRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Test
    public void testInsert() {

        Long cafeid = 1L;
        Cafe cafeAAA = Cafe.builder().id(cafeid).build();

        String imgUrl="https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/cafe/cafeAAA/cafeAAA01.jpg";
        CafeImage cafeImage01 = CafeImage.builder()
                .imageUrl(imgUrl)
                .cafe(cafeAAA)
                .build();
        cafeImageRepository.save(cafeImage01);

        imgUrl="https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/cafe/cafeAAA/cafeAAA02.jpg";
        CafeImage cafeImage02 = CafeImage.builder()
                .imageUrl(imgUrl)
                .cafe(cafeAAA)
                .build();

        cafeImageRepository.save(cafeImage02);

    }

    @Test
    public void testGetCafeImages() {
        Long cafeId = 1L;

        List<CafeImage> results = cafeImageRepository.listOfCafeImages(cafeId);
        results.forEach(cafeImage -> {
            System.out.println("cafeImage.getImageUrl() = " + cafeImage.getImageUrl());
        });
    }
}