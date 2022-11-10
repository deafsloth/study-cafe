package com.jdong.studycafe.orders.domain;

import com.jdong.studycafe.Members.domain.Member;
import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "OrderList")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beverage_id")
    private Beverage beverage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Column(name = "charged_time", updatable = false)
    private Long chargedTime;

    @Column(updatable = false)
    private Long cost;

}
