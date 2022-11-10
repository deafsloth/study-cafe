package com.jdong.studycafe.cafes.service;

import com.jdong.studycafe.cafes.dto.CafeDTO;
import com.jdong.studycafe.cafes.dto.CafeMapRequestDTO;
import com.jdong.studycafe.cafes.dto.CafeWithImageDto;

import java.util.List;

public interface CafeService {
    Long registerCafe(CafeDTO cafeDTO);
    CafeDTO findByCafeName(String name);
    List<CafeDTO> findCafeWithLocation(CafeMapRequestDTO cafeMapRequestDTO);
    List<CafeDTO> findAllCafe();

    CafeDTO findCafeById(Long id);
    CafeWithImageDto findCafeWithImageById(Long id);
}
