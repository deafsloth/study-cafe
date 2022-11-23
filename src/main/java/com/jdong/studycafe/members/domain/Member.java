package com.jdong.studycafe.members.domain;

import com.jdong.studycafe.common.domain.BaseTimeEntity;
import com.jdong.studycafe.favorites.domain.Favorite;
import com.jdong.studycafe.orders.domain.Order;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String email;
    private String roles;
    private String providerId;
    private String provider;
    private String name;

    // 추후 enum 으로 교체
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @Column(name = "special_credit")
    private int specialCredit;
    @Column(name = "general_credit")
    private int generalCredit;

    @OneToMany(mappedBy = "member")
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();
}
