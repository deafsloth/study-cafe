package com.jdong.studycafe.cafes.repository;

import com.jdong.studycafe.cafes.domain.CafeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CafeImageRepository extends JpaRepository<CafeImage, Long> {

    @Query("select c from CafeImage c where c.cafe.id=:cafeId")
    List<CafeImage> listOfCafeImages(Long cafeId);

}
