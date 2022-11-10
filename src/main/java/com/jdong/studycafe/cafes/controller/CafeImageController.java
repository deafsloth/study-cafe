package com.jdong.studycafe.cafes.controller;

import com.jdong.studycafe.cafes.dto.CafeImageDTO;
import com.jdong.studycafe.cafes.service.CafeImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cafes/images")
@RequiredArgsConstructor
public class CafeImageController {

    private final CafeImageService cafeImageService;

    @GetMapping(value = "/{cafeId}")
    public ResponseEntity<Map<String, Object>> getCafeImagesByCafeId(@PathVariable(value = "cafeId") Long cafeId) {
        List<CafeImageDTO> cafeImagesByCafeId = cafeImageService.getCafeImagesByCafeId(cafeId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("cafeId", String.valueOf(cafeId));
        resultMap.put("cafeImageList", cafeImagesByCafeId);

        return ResponseEntity.ok().body(resultMap);
    }

}
