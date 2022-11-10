package com.jdong.studycafe.favorites.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class FavoriteDTO {

    private Long favoriteId;

    private Long cafeId;
    private String cafeName;

    private Long beverageId;
    private String beverageImage;
    private String beverageName;
    private int price;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    @QueryProjection
    public FavoriteDTO(Long favoriteId, Long cafeId, String cafeName, Long beverageId, String beverageImage, String beverageName, int price, LocalDateTime createTime, LocalDateTime modifiedTime) {
        this.favoriteId = favoriteId;
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.beverageId = beverageId;
        this.beverageImage = beverageImage;
        this.beverageName = beverageName;
        this.price = price;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }
}
