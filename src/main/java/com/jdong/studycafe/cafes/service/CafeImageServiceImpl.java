package com.jdong.studycafe.cafes.service;

import com.jdong.studycafe.cafes.dto.CafeImageDTO;
import com.jdong.studycafe.cafes.domain.CafeImage;
import com.jdong.studycafe.cafes.repository.CafeImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CafeImageServiceImpl implements CafeImageService {
    private final CafeImageRepository cafeImageRepository;

    @Override
    public List<CafeImageDTO> getCafeImagesByCafeId(Long cafeId) {
        List<CafeImage> cafeImages = cafeImageRepository.listOfCafeImages(cafeId);
        List<CafeImageDTO> collect = cafeImages.stream()
                .map(cafeImage -> CafeImageDTO.builder()
                        .cafeImageId(cafeImage.getId())
                        .cafeId(cafeImage.getCafe().getId())
                        .imageUrl(cafeImage.getImageUrl())
                        .createdDate(cafeImage.getCreatedDate())
                        .modifiedDate(cafeImage.getModifiedDate())
                        .build())
                .collect(Collectors.toList());
        return collect;
    }
}
