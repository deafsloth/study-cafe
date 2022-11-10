package com.jdong.studycafe.beverages.service;

import com.jdong.studycafe.beverages.dto.BeverageDTO;

public interface BeverageService {
    BeverageDTO getBeverageByBeverageId(Long beverageId);
}
