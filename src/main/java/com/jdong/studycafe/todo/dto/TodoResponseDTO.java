package com.jdong.studycafe.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {
    private Long todoId;
    private Long memberId;
    private String title;
    private String content;
    private boolean isFinish;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
