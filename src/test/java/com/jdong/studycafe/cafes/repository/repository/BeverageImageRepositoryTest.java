package com.jdong.studycafe.cafes.repository.repository;

import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.beverages.domain.BeverageImage;
import com.jdong.studycafe.beverages.repository.BeverageImageRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class BeverageImageRepositoryTest {

    @Autowired
    BeverageImageRepository beverageImageRepository;

    @Test
    public void getBeverageListByBeverageID() {
        List<BeverageImage> beverageImageDTOS = beverageImageRepository.listOfBeverageImage(1L);
        beverageImageDTOS.forEach(beverageImageDTO -> System.out.println("beverageImageDTO.getImageUrl() = " + beverageImageDTO.getImageUrl()));

    }

    @Test
    public void 음료_이미지_저장() {

        Beverage beverage = Beverage.builder().id(1L).build();
        BeverageImage beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/americano/americano01.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);


        beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/americano/americano02.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);

        beverage = Beverage.builder().id(2L).build();
        beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/cafelatte/cafelatte01.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);


        beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/cafelatte/cafelatte02.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);
    }

}