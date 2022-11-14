package com.jdong.studycafe.cafes.controller;

import com.jdong.studycafe.cafes.dto.CafeDTO;
import com.jdong.studycafe.cafes.dto.CafeImageDTO;
import com.jdong.studycafe.cafes.dto.CafeMapRequestDTO;
import com.jdong.studycafe.cafes.service.CafeImageService;
import com.jdong.studycafe.cafes.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cafes")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;
    private final CafeImageService cafeImageService;

    @GetMapping("/list/location")
    public ResponseEntity<Map<String, Object>> findCafeWithLocation(@RequestBody @Validated CafeMapRequestDTO cafeMapRequestDTO) {
        List<CafeDTO> cafeWithLocation = cafeService.findCafeWithLocation(cafeMapRequestDTO);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("RequestPosition", cafeMapRequestDTO);
        resultMap.put("cafeList", cafeWithLocation);

        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/list/all")
    public ResponseEntity<Map<String, Object>> getAllCafeList() {
        List<CafeDTO> allCafe = cafeService.findAllCafe();
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("cafeList", allCafe);

        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/{cafeId}")
    public ResponseEntity<Map<String, Object>> getCafeById(@PathVariable(value = "cafeId") Long cafeId) {
        CafeDTO cafe = cafeService.findCafeById(cafeId);
        List<CafeImageDTO> cafeImagesByCafeId = cafeImageService.getCafeImagesByCafeId(cafeId);

        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("cafeId", cafeId);
        resultMap.put("cafeImages", cafeImagesByCafeId);
        resultMap.put("cafeInfo", cafe);

        return ResponseEntity.ok().body(resultMap);
    }

}

