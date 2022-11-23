package com.jdong.studycafe.orders.repository;

import com.jdong.studycafe.favorites.domain.Favorite;
import com.jdong.studycafe.orders.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {
    @Query("select s from Study s where s.member.id=:memberId")
    List<Study> findByMemberId(Long memberId);

    Long deleteAllByMemberId(Long memberId);

}
