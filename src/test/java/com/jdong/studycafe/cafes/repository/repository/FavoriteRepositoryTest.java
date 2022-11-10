package com.jdong.studycafe.cafes.repository.repository;

import com.jdong.studycafe.favorites.domain.Favorite;
import com.jdong.studycafe.favorites.repository.FavoriteRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class FavoriteRepositoryTest {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Test
    public void findByMemberId() {
        Long memberId = 1L;
        List<Favorite> favoriteList = favoriteRepository.findByMemberId(memberId);

        favoriteList.stream().forEach(favorite -> System.out.println("favorite.getMember().getId() = " + favorite.getMember().getId()));

    }
    @Test
    public void 좀돼주세요() {
        Long memberId = 1L;
        List<Favorite> favoriteList = favoriteRepository.findFavoriteListByMemberId(memberId);
        System.out.println("12312312312312");

        favoriteList.stream().forEach(favorite -> System.out.println("favorite = " + favorite.getCafe().getName()));

    }

}