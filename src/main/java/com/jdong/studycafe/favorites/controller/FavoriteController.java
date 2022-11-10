package com.jdong.studycafe.favorites.controller;

import com.jdong.studycafe.common.domain.ErrorResponse;
import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.FavoriteRequestDTO;
import com.jdong.studycafe.cafes.service.CafeService;
import com.jdong.studycafe.favorites.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("")
    public ResponseEntity<HashMap<String, Object>> getFavoriteListByMemberId(
            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
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

        HashMap<String, Object> result = favoriteService.deleteFavorite(favoriteId, userDetails.getMember().getId());

        return ResponseEntity.ok().body(result);
    }

}
