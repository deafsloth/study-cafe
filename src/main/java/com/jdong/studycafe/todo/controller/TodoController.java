package com.jdong.studycafe.todo.controller;

import com.jdong.studycafe.config.auth.CustomUserDetails;
import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.service.FavoriteService;
import com.jdong.studycafe.todo.dto.TodoListRequestDTO;
import com.jdong.studycafe.todo.dto.TodoListResponseDTO;
import com.jdong.studycafe.todo.dto.TodoRequestDTO;
import com.jdong.studycafe.todo.dto.TodoResponseDTO;
import com.jdong.studycafe.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/v2/todolist")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("")
    public ResponseEntity<HashMap<String, Object>> getTodoListByMemberId(
            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long memberId = userDetails.getMember().getId();
        TodoListResponseDTO todoList = todoService.getTodoList(memberId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("todoList", todoList);
        result.put("memberId", memberId);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<?> postTodoList(
            @Validated @RequestBody final TodoListRequestDTO todoListRequestDTO,
            Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println("todoListRequestDTO = " + todoListRequestDTO.getTodoList().get(0).toString());
        TodoListResponseDTO todoListResponseDTO = todoService.postTodoList(userDetails.getMember().getId(), todoListRequestDTO);

        HashMap<String, Object> result = new HashMap<>();
        result.put("todoListResponseDTO", todoListResponseDTO);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{todoId}")
    public ResponseEntity<HashMap<String, Object>> todoCheck(
            @PathVariable Long todoId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        TodoResponseDTO todoResponseDTO = todoService.todoCheck(todoId, userDetails.getMember().getId());
        HashMap<String, Object> result = new HashMap<>();
        result.put("todoResponseDTO", todoResponseDTO);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<HashMap<String, Object>> deleteTodo(
            @PathVariable Long todoId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        TodoResponseDTO todoResponseDTO = todoService.deleteTodo(userDetails.getMember().getId(),todoId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("todoResponseDTO", todoResponseDTO);

        return ResponseEntity.ok().body(result);
    }
}
