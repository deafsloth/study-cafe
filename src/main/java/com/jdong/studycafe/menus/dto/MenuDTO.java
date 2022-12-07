package com.jdong.studycafe.menus.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class MenuDTO {
    private Long menuId;
    private Long cafeId;
    private String cafeName;

    private Long beverageId;
    private String beverageName;
    private Integer price;
    private String mainImageUrl;
    private Boolean isPremium;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public MenuDTO(Long menuId, Long cafeId, String cafeName, Long beverageId, String beverageName, Integer price, String mainImageUrl, Boolean isPremium, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.menuId = menuId;
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.price = price;
        this.mainImageUrl = mainImageUrl;
        this.isPremium = isPremium;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
