package com.jdong.studycafe.orders.repository;

import com.jdong.studycafe.orders.domain.QStudy;
import com.jdong.studycafe.orders.dto.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jdong.studycafe.beverages.domain.QBeverage.beverage;
import static com.jdong.studycafe.cafes.domain.QCafe.cafe;
import static com.jdong.studycafe.orders.domain.QOrder.order;
import static com.jdong.studycafe.orders.domain.QStudy.study;

@Repository
@RequiredArgsConstructor
public class StudyQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<StudyDTO> findStudyByMemberId(Long memberId) {
        List<StudyDTO> result = jpaQueryFactory
                .select(new QStudyDTO(
                        study.id,
                        study.member.id,
                        study.cafe.id,
                        study.cafe.name,
                        study.beverage.id,
                        study.beverage.name,
                        study.chargedTime,
                        study.startTime,
                        study.endTime
                ))
                .from(study)
                .leftJoin(study.beverage, beverage)
                .leftJoin(study.cafe, cafe)
                .where(study.member.id.eq(memberId))
                .fetch();
        return result;
    }

}
