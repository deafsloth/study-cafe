package com.jdong.studycafe.beverages.service;

import com.jdong.studycafe.beverages.dto.BeverageImageDTO;
import com.jdong.studycafe.beverages.domain.BeverageImage;
import com.jdong.studycafe.beverages.repository.BeverageImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BeverageImageServiceImpl implements BeverageImageService {

    private final BeverageImageRepository beverageImageRepository;

    @Override
    public List<BeverageImageDTO> getBeverageImageByBeverageId(Long beverageId) {
        List<BeverageImage> listOfBeverageImage = beverageImageRepository.listOfBeverageImage(beverageId);

        List<BeverageImageDTO> collect = listOfBeverageImage.stream()
                .map(beverageImage -> BeverageImageDTO.builder()
                        .beverageImageId(beverageImage.getId())
                        .beverageId(beverageImage.getBeverage().getId())
                        .imageUrl(beverageImage.getImageUrl())
                        .createdDate(beverageImage.getCreatedDate())
                        .modifiedDate(beverageImage.getModifiedDate())
                        .build())
                .collect(Collectors.toList());

        return collect;

    }
}
