package com.jdong.studycafe.todo.service;

import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.dto.FavoriteRequestDTO;
import com.jdong.studycafe.todo.dto.TodoListRequestDTO;
import com.jdong.studycafe.todo.dto.TodoListResponseDTO;
import com.jdong.studycafe.todo.dto.TodoRequestDTO;
import com.jdong.studycafe.todo.dto.TodoResponseDTO;

import java.util.HashMap;
import java.util.List;

public interface TodoService {
    TodoListResponseDTO postTodoList(Long memberId, TodoListRequestDTO todoListRequestDTO);

    TodoResponseDTO todoCheck(Long todoId, Long memberId);

    TodoListResponseDTO getTodoList(Long memberId);

    TodoResponseDTO deleteTodo(Long memberId, Long todoId);

}
