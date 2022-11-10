package com.jdong.studycafe.cafes.repository.repository;

import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.beverages.repository.BeverageRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class BeverageRepositoryTest {
    @Autowired
    BeverageRepository beverageRepository;

    @Test
    public void 음료_생성() {
        Beverage beverage = Beverage.builder()
                .name("Americano")
                .price(4000)
                .isPremium(false)
                .description("this is Americano")
                .build();

        beverageRepository.save(beverage);
        beverage = Beverage.builder()
                .name("Cafe Latte")
                .price(5000)
                .isPremium(true)
                .description("this is Cafe Latte")
                .build();

        beverageRepository.save(beverage);
    }
}