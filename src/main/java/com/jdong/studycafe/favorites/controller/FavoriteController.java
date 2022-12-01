package com.jdong.studycafe.favorites.controller;

import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.FavoriteRequestDTO;
import com.jdong.studycafe.favorites.service.FavoriteService;
import com.jdong.studycafe.orders.exception.IsStudyingException;
import com.jdong.studycafe.orders.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/v2/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final StudyService studyService;

    @GetMapping("")
    public ResponseEntity<HashMap<String, Object>> getFavoriteListByMemberId(
            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Boolean isStudying = studyService.isStudying(userDetails.getMember().getId());
        if (isStudying == Boolean.TRUE) {
            throw new IsStudyingException(userDetails.getMember().getId().toString());
        }
        Long memberId = userDetails.getMember().getId();
        List<FavoriteDTO> favoriteList = favoriteService.getFavoriteListByMemberId(memberId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("favoriteList", favoriteList);
        result.put("memberId", memberId);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<?> addFavorite(
            @Validated @RequestBody final FavoriteRequestDTO favoriteRequestDTO,
            Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Boolean isStudying = studyService.isStudying(userDetails.getMember().getId());
        if (isStudying == Boolean.TRUE) {
            throw new IsStudyingException(userDetails.getMember().getId().toString());
        }
        FavoriteDTO favoriteDTO = favoriteService.addFavorite(favoriteRequestDTO, userDetails.getMember().getId());

        HashMap<String, Object> result = new HashMap<>();
        result.put("savedFavorite", favoriteDTO);
        result.put("message", "add Favorite success");

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<HashMap<String, Object>> deleteFavorite(
            @PathVariable Long favoriteId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Boolean isStudying = studyService.isStudying(userDetails.getMember().getId());
        if (isStudying == Boolean.TRUE) {
            throw new IsStudyingException(userDetails.getMember().getId().toString());
        }

        HashMap<String, Object> result = favoriteService.deleteFavorite(favoriteId, userDetails.getMember().getId());

        return ResponseEntity.ok().body(result);
    }

}
