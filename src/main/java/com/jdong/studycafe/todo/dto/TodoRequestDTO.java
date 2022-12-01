package com.jdong.studycafe.todo.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDTO {
    private String title;
    private String content;
}
