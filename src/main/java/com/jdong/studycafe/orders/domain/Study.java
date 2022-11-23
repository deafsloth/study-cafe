package com.jdong.studycafe.orders.domain;

import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.common.domain.BaseTimeEntity;
import com.jdong.studycafe.members.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "StudyList")
public class Study extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
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

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
