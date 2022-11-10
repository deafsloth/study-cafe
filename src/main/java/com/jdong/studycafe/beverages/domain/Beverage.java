package com.jdong.studycafe.beverages.domain;

import com.jdong.studycafe.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@ToString
@Entity
public class Beverage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beverage_id")
    private long id;

    private String name;
    private int price;
    private boolean isPremium;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beverage")
    private List<BeverageImage> beverageImage = new ArrayList<>();

    private String mainImageUrl;
}
