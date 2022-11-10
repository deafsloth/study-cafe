package com.jdong.studycafe.cafes.service;

import com.jdong.studycafe.cafes.dto.CafeImageDTO;

import java.util.List;

public interface CafeImageService {
    List<CafeImageDTO> getCafeImagesByCafeId(Long cafeId);
}
