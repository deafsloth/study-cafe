package com.jdong.studycafe.favorites.repository;

import com.jdong.studycafe.favorites.domain.QFavorite;
import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.QFavoriteDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jdong.studycafe.beverages.domain.QBeverage.beverage;
import static com.jdong.studycafe.cafes.domain.QCafe.cafe;
import static com.jdong.studycafe.favorites.domain.QFavorite.favorite;

@Repository
@RequiredArgsConstructor
public class FavoriteQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<FavoriteDTO> findFavoriteListByMemberId(Long memberId) {
        List<FavoriteDTO> result = jpaQueryFactory
                .select(new QFavoriteDTO(
                        favorite.id,
                        favorite.cafe.id,
                        favorite.cafe.name,
                        beverage.id,
                        beverage.mainImageUrl,
                        beverage.name,
                        beverage.price,
                        favorite.createdDate,
                        favorite.modifiedDate))
                .from(favorite)
                .leftJoin(favorite.beverage, beverage)
                .leftJoin(favorite.cafe, cafe)
                .where(favorite.member.id.eq(memberId))
                .fetch();
        return result;
    }
}
