package com.jdong.studycafe.favorites.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteListResponseDTO {

    private Long memberId;
    private String username;
    private String name;

    private List<FavoriteDTO> favorites;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

}
