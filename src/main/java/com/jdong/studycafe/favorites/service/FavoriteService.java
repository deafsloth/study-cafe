package com.jdong.studycafe.favorites.service;

import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.FavoriteRequestDTO;

import java.util.HashMap;
import java.util.List;

public interface FavoriteService {
    List<FavoriteDTO> getFavoriteListByMemberId(Long memberId);

    FavoriteDTO addFavorite(FavoriteRequestDTO favoriteRequestDTO,Long memberId);

    HashMap<String, Object> deleteFavorite(Long favoriteId, Long memberId);

}
