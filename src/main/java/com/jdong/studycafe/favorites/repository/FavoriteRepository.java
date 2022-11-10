package com.jdong.studycafe.favorites.repository;

import com.jdong.studycafe.favorites.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("select f from Favorite f where f.member.id=:memberId")
    List<Favorite> findByMemberId(Long memberId);

    @Query("select f " +
            "from Favorite f " +
                "left join fetch f.beverage b " +
                "left join fetch f.cafe c " +
            "where f.member.id=:memberId")
    List<Favorite> findFavoriteListByMemberId(Long memberId);

    @Query("select f " +
            "from Favorite f " +
                "left join fetch f.beverage b " +
                "left join fetch f.cafe c " +
                "left join fetch f.member m " +
            "where f.id=:favoriteId")
    Favorite findByFavoriteId(Long favoriteId);

}