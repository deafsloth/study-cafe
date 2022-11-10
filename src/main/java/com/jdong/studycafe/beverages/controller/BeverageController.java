package com.jdong.studycafe.beverages.controller;

import com.jdong.studycafe.beverages.dto.BeverageDTO;
import com.jdong.studycafe.beverages.dto.BeverageImageDTO;
import com.jdong.studycafe.beverages.service.BeverageImageService;
import com.jdong.studycafe.beverages.service.BeverageService;
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
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/beverage")
public class BeverageController {

    private final BeverageService beverageService;
    private final BeverageImageService beverageImageService;

    @GetMapping("/{beverageId}")
    public ResponseEntity<Map<String, Object>> getBeverageWithBeverageId(@PathVariable(value = "beverageId") Long beverageId) {
        BeverageDTO beverageByBeverageId = beverageService.getBeverageByBeverageId(beverageId);
        List<BeverageImageDTO> beverageImageByBeverageId = beverageImageService.getBeverageImageByBeverageId(beverageId);

        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("beverageId", beverageId);
        resultMap.put("beverageInfo", beverageByBeverageId);
        resultMap.put("beverageImages", beverageImageByBeverageId);

        return ResponseEntity.ok().body(resultMap);
    }
}
