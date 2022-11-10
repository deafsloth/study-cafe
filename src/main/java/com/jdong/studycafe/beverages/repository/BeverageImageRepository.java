package com.jdong.studycafe.beverages.repository;

import com.jdong.studycafe.beverages.domain.BeverageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BeverageImageRepository extends JpaRepository<BeverageImage, Long> {
    @Query("select b from BeverageImage b where b.beverage.id=:beverageId")
    List<BeverageImage> listOfBeverageImage(Long beverageId);
}

