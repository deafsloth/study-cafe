package com.jdong.studycafe.todo.dto;

import com.jdong.studycafe.favorites.dto.FavoriteDTO;
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
public class TodoListResponseDTO {

    private Long memberId;
    private String name;

    private List<TodoResponseDTO> todoLists;

}
