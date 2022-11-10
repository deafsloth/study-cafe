package com.jdong.studycafe.service;

import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.FavoriteRequestDTO;
import com.jdong.studycafe.favorites.service.FavoriteService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class FavoriteServiceImplTest {

    @Autowired
    private FavoriteService favoriteService;

    @Test
    void addFavorite() {
        FavoriteRequestDTO favoriteRequestDTO = FavoriteRequestDTO.builder()
                .beverageId(1L)
                .cafeId(2L)
                .build();

        FavoriteDTO favoriteDTO = favoriteService.addFavorite(favoriteRequestDTO, 1L);
        System.out.println("favoriteDTO = " + favoriteDTO);

    }

    @Test
    void getFavoriteListByMemberId() {
    }
}