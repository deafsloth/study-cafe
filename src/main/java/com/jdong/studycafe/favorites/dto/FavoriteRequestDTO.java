package com.jdong.studycafe.favorites.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRequestDTO {

    @NotNull(message = "beverageId 값은 비어었을 수 없습니다.")
    @Min(value=1,message = "beverageId 값은 1이상이어야 합니다.")
    private Long beverageId;

    @NotNull(message = "CafeId 값은 비어었을 수 없습니다.")
    @Min(value=1,message = "CafeId 값은 1이상이어야 합니다.")
    private Long cafeId;
}
