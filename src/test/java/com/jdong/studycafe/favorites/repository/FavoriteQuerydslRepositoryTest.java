package com.jdong.studycafe.favorites.repository;

import com.jdong.studycafe.beverages.domain.QBeverage;
import com.jdong.studycafe.favorites.domain.Favorite;
import com.jdong.studycafe.favorites.domain.QFavorite;
import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.jdong.studycafe.beverages.domain.QBeverage.beverage;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class FavoriteQuerydslRepositoryTest {

    @Autowired
    private FavoriteQuerydslRepository favoriteQuerydslRepository;
    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
    public void testFavoriteListByMemberId() {
        List<FavoriteDTO> favoriteListByMemberId = favoriteQuerydslRepository.findFavoriteListByMemberId(1L);

        favoriteListByMemberId.stream().forEach(favorite -> System.out.println("favorite = " + favorite.getCafeName()));
//        log.info(favoriteListByMemberId.stream().forEach(favoriteDTO -> System.out.println("favoriteDTO = " + favoriteDTO)));
    }

    @Test
    public void queryDslTest() {
        QFavorite favorite = QFavorite.favorite;
        QBeverage beverage = QBeverage.beverage;

        List<Favorite> favorites = queryFactory.selectFrom(favorite)
                .leftJoin(favorite.beverage, beverage).fetchJoin()
                .where(favorite.member.id.eq(1L))
                .fetch();
        favorites.stream().forEach(f -> System.out.println("favorite.beverage.id = " + f.getId()));
        favorites.stream().forEach(f -> System.out.println("favorite.beverage.id = " + f.getCafe().getName()));
        favorites.stream().forEach(f -> System.out.println("favorite.beverage.id = " + f.getBeverage().getName()));

    }
}