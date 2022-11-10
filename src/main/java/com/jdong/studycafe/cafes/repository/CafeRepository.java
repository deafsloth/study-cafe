package com.jdong.studycafe.cafes.repository;

import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.cafes.service.search.CafeSearch;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe,Long>, CafeSearch {
    @Query(value=
            "SELECT * " +
            "from cafe " +
            "where ST_DistanceSphere(location, :p) < :distanceM"
            , nativeQuery = true)
    List<Cafe> findNearWithinDistance(Point p, double distanceM);
    Optional< Cafe> findByName(String name);

}
