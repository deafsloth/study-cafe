package com.jdong.studycafe.beverages.repository;

import com.jdong.studycafe.beverages.domain.Beverage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BeverageQuerydslRepositoryTest {
    @Autowired
    private BeverageQuerydslRepository beverageQuerydslRepository;

    @Test
    public void 살려줘() {
        List<Beverage> beverageById = beverageQuerydslRepository.findBeverageById(1L);

    }

}