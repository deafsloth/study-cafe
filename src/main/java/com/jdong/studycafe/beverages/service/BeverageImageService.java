package com.jdong.studycafe.beverages.service;

import com.jdong.studycafe.beverages.dto.BeverageImageDTO;

import java.util.List;

public interface BeverageImageService {
    public List<BeverageImageDTO> getBeverageImageByBeverageId(Long beverageId);

}


