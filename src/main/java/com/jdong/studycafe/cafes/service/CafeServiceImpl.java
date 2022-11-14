package com.jdong.studycafe.cafes.service;

import com.jdong.studycafe.cafes.dto.CafeDTO;
import com.jdong.studycafe.cafes.dto.CafeMapRequestDTO;
import com.jdong.studycafe.cafes.dto.CafeWithImageDto;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.cafes.exception.CafeNotFoundException;
import com.jdong.studycafe.cafes.repository.CafeImageRepository;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Service
public class CafeServiceImpl implements CafeService {
    private final ModelMapper modelMapper;
    private final CafeRepository cafeRepository;

    private final CafeImageRepository cafeImageRepository;

    @Override
    public CafeWithImageDto findCafeWithImageById(Long id) {
//        Optional<Cafe> optionalCafe = cafeRepository.findById(id);
//        if (!optionalCafe.isPresent()) {
//            throw new IllegalArgumentException();
//        }
//        Cafe cafe = optionalCafe.get();
//
//        cafeImageRepository.
//
        return null;
    }

    @Override
    public CafeDTO findCafeById(Long id) {
        Optional<Cafe> optionalCafe = cafeRepository.findById(id);
        if (!optionalCafe.isPresent()) {
            throw new CafeNotFoundException(id);
        }
        Cafe cafe = optionalCafe.get();
        CafeDTO result = CafeDTO.builder()
                .id(cafe.getId())
                .name(cafe.getName())
                .description(cafe.getDescription())
                .latitude(cafe.getLocation().getCoordinate().x)
                .longitude(cafe.getLocation().getCoordinate().y)
                .isPremium(cafe.isPremium())
                .region(cafe.getRegion())
                .createdDate(cafe.getCreatedDate())
                .modifiedDate(cafe.getModifiedDate())
                .build();
        return result;
    }

    @Override
    public List<CafeDTO> findAllCafe() {
        List<Cafe> all = cafeRepository.findAll();
        List<CafeDTO> collect = all.stream()
                .map(cafe -> CafeDTO.builder()
                        .id(cafe.getId())
                        .name(cafe.getName())
                        .description(cafe.getDescription())
                        .latitude(cafe.getLocation().getCoordinate().x)
                        .longitude(cafe.getLocation().getCoordinate().y)
                        .isPremium(cafe.isPremium())
                        .region(cafe.getRegion())
                        .createdDate(cafe.getCreatedDate())
                        .modifiedDate(cafe.getModifiedDate())
                        .build())
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<CafeDTO> findCafeWithLocation(CafeMapRequestDTO cafeMapRequestDTO) {
        String longitude = cafeMapRequestDTO.getLongitude();
        String latitude = cafeMapRequestDTO.getLatitude();
        Double radius = cafeMapRequestDTO.getRadius();
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        Point point = null;
        try {
            point = (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Cafe> cafeList = cafeRepository.findNearWithinDistance(point, radius);
        System.out.println("cafeServiceImpl.findNearWithinDistance");
        List<CafeDTO> collect = cafeList.stream()
                .map(cafe -> CafeDTO.builder()
                        .id(cafe.getId())
                        .name(cafe.getName())
                        .description(cafe.getDescription())
                        .latitude(cafe.getLocation().getCoordinate().x)
                        .longitude(cafe.getLocation().getCoordinate().y)
                        .isPremium(cafe.isPremium())
                        .region(cafe.getRegion())
                        .createdDate(cafe.getCreatedDate())
                        .modifiedDate(cafe.getModifiedDate())
                        .build())
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public Long registerCafe(CafeDTO cafeDTO) {
        Cafe cafe = modelMapper.map(cafeDTO, Cafe.class);
        long id = cafeRepository.save(cafe).getId();

        return id;
    }

    @Override
    public CafeDTO findByCafeName(String name) {
        Optional<Cafe> result = cafeRepository.findByName(name);
        Cafe cafe = result.orElseThrow();
        CafeDTO cafeDTO = modelMapper.map(cafe, CafeDTO.class);
        return cafeDTO;
    }

}
