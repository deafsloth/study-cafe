package com.jdong.studycafe.todo.domain;

import com.jdong.studycafe.common.domain.BaseTimeEntity;
import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.members.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String content;
    private Boolean isFinish;

}

