package com.jdong.studycafe.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoListRequestDTO {
    @NotEmpty(message = "todoList는 비어있을 수 없습니다.")
    private List<TodoRequestDTO> todoList;
}
