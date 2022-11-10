package com.jdong.studycafe.beverages.service;

import com.jdong.studycafe.beverages.dto.BeverageDTO;
import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.beverages.repository.BeverageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class BeverageServiceImpl implements BeverageService {

    private final BeverageRepository beverageRepository;

    @Override
    public BeverageDTO getBeverageByBeverageId(Long beverageId) {
        Optional<Beverage> optionalBeverage = beverageRepository.findById(beverageId);
        Beverage beverage = optionalBeverage.orElseThrow();

        BeverageDTO beverageDTO = BeverageDTO.builder()
                .beverageId(beverage.getId())
                .name(beverage.getName())
                .price(beverage.getPrice())
                .isPremium(beverage.isPremium())
                .description(beverage.getDescription())
                .createdDate(beverage.getCreatedDate())
                .modifiedDate(beverage.getModifiedDate())
                .build();

        return beverageDTO;
    }
}
