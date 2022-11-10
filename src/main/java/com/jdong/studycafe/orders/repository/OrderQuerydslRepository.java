package com.jdong.studycafe.orders.repository;

import com.jdong.studycafe.orders.dto.OrderCountDTO;
import com.jdong.studycafe.orders.dto.OrderDTO;
import com.jdong.studycafe.orders.dto.QOrderCountDTO;
import com.jdong.studycafe.orders.dto.QOrderDTO;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jdong.studycafe.beverages.domain.QBeverage.beverage;
import static com.jdong.studycafe.cafes.domain.QCafe.cafe;
import static com.jdong.studycafe.orders.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<OrderDTO> findOrderListByMemberId(Long memberId) {
        List<OrderDTO> result = jpaQueryFactory
                .select(new QOrderDTO(
                        order.id,
                        order.cafe.id,
                        order.cafe.name,
                        order.beverage.id,
                        order.beverage.name,
                        order.beverage.mainImageUrl,
                        order.chargedTime,
                        order.cost,
                        order.createdDate,
                        order.modifiedDate
                ))
                .from(order)
                .leftJoin(order.beverage, beverage)
                .leftJoin(order.cafe, cafe)
                .where(order.member.id.eq(memberId))
                .fetch();
        return result;
    }

    public List<OrderCountDTO> getMostOrderList() {
        NumberPath<Long> aliasCount = Expressions.numberPath(Long.class, "count");
        List<OrderCountDTO> result = jpaQueryFactory
                .select(new QOrderCountDTO(
                        order.cafe.id,
                        order.beverage.id,
                        order.id.count().as("count"))
                )
                .from(order)
                .leftJoin(order.beverage, beverage)
                .leftJoin(order.cafe, cafe)
                .groupBy(order.beverage.id)
                .groupBy(order.cafe.id)
                .orderBy(aliasCount.desc())
                .fetch();
        return result;
    }

//    public List<OrderCountDTO> getMostOrderList() {
//        List<OrderCountDTO> result = jpaQueryFactory
//                .select(new QOrderCountDTO(
//                        order.cafe.id,
//                        order.cafe.name,
//                        order.beverage.id,
//                        order.beverage.name,
//                        order.beverage.mainImageUrl,
//                        order.id.count().as("count"))
//                )
//                .from(order)
//                .leftJoin(order.beverage, beverage)
//                .leftJoin(order.cafe, cafe)
//                .groupBy(order.beverage.id)
//                .groupBy(order.cafe.id)
//                .fetch();
//        return result;
//    }
}
