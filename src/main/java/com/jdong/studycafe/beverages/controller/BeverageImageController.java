package com.jdong.studycafe.beverages.controller;

import com.jdong.studycafe.beverages.dto.BeverageImageDTO;
import com.jdong.studycafe.beverages.service.BeverageImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/beverage/images")
@RequiredArgsConstructor
@Log4j2
public class BeverageImageController {

    private final BeverageImageService beverageImageService;

    @GetMapping(value = "/{beverageId}")
    public ResponseEntity<Map<String, Object>> getBeverageImagesByBeverageId(@PathVariable(value = "beverageId") Long beverageId) {
        List<BeverageImageDTO> beverageImageDTOList = beverageImageService.getBeverageImageByBeverageId(beverageId);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("beverageId", beverageId);
        resultMap.put("beverageImages", beverageImageDTOList);

        return ResponseEntity.ok().body(resultMap);
    }
}
