package com.jdong.studycafe.beverages.repository;

import com.jdong.studycafe.beverages.domain.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

}
