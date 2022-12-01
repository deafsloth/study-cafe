package com.jdong.studycafe.todo.service;

import com.jdong.studycafe.favorites.domain.Favorite;
import com.jdong.studycafe.favorites.dto.FavoriteDTO;
import com.jdong.studycafe.favorites.exception.FavoriteNotFoundException;
import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.members.exception.MemberNotFoundException;
import com.jdong.studycafe.members.repository.MemberRepository;
import com.jdong.studycafe.todo.domain.Todo;
import com.jdong.studycafe.todo.dto.TodoListRequestDTO;
import com.jdong.studycafe.todo.dto.TodoListResponseDTO;
import com.jdong.studycafe.todo.dto.TodoRequestDTO;
import com.jdong.studycafe.todo.dto.TodoResponseDTO;
import com.jdong.studycafe.todo.exception.TodoNotFoundException;
import com.jdong.studycafe.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Override
    public TodoListResponseDTO postTodoList(Long memberId, TodoListRequestDTO todoListRequestDTO) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()->new MemberNotFoundException(memberId));
        List<TodoResponseDTO> todoLists = new ArrayList<>();
        
        for (TodoRequestDTO requestDTO : todoListRequestDTO.getTodoList()) {
            System.out.println("requestDTO = " + requestDTO);
            Todo todo = Todo.builder()
                    .member(member)
                    .title(requestDTO.getTitle())
                    .content(requestDTO.getContent())
                    .isFinish(Boolean.FALSE)
                    .build();
            Todo save = todoRepository.save(todo);
            System.out.println("save = " + save.getCreatedDate());
            TodoResponseDTO responseDTO = TodoResponseDTO.builder()
                    .todoId(save.getId())
                    .memberId(save.getMember().getId())
                    .title(save.getTitle())
                    .content(save.getContent())
                    .isFinish(save.getIsFinish())
                    .createTime(save.getCreatedDate())
                    .modifiedTime(save.getModifiedDate())
                    .build();
            todoLists.add(responseDTO);
        }
        TodoListResponseDTO result = TodoListResponseDTO.builder()
                .memberId(memberId)
                .name(member.getName())
                .todoLists(todoLists)
                .build();
        return result;
    }

    @Override
    public TodoResponseDTO todoCheck(Long todoId, Long memberId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo todo = optionalTodo.orElseThrow(() -> new TodoNotFoundException(todoId));

        Boolean isFinish = todo.getIsFinish();
        if (isFinish == Boolean.FALSE) {
            isFinish = Boolean.TRUE;
        } else {
            isFinish = Boolean.FALSE;
        }
        todo.setIsFinish(isFinish);

        Todo save = todoRepository.save(todo);
        TodoResponseDTO responseDTO = TodoResponseDTO.builder()
                .todoId(save.getId())
                .memberId(save.getMember().getId())
                .title(save.getTitle())
                .content(save.getContent())
                .isFinish(save.getIsFinish())
                .createTime(save.getCreatedDate())
                .modifiedTime(save.getModifiedDate())
                .build();
        return responseDTO;
    }

    @Override
    public TodoListResponseDTO getTodoList(Long memberId) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()->new MemberNotFoundException(memberId));
        List<Todo> byMemberId = todoRepository.findByMemberId(memberId);

        List<TodoResponseDTO> todoLists = new ArrayList<>();
        for (Todo todo : byMemberId) {
            TodoResponseDTO build = TodoResponseDTO.builder()
                    .todoId(todo.getId())
                    .memberId(todo.getMember().getId())
                    .title(todo.getTitle())
                    .content(todo.getContent())
                    .isFinish(todo.getIsFinish())
                    .createTime(todo.getCreatedDate())
                    .modifiedTime(todo.getModifiedDate())
                    .build();
            todoLists.add(build);
        }
        TodoListResponseDTO result = TodoListResponseDTO.builder()
                .memberId(memberId)
                .name(member.getName())
                .todoLists(todoLists)
                .build();
        return result;
    }

    @Override
    public TodoResponseDTO deleteTodo(Long memberId, Long todoId) {
        Optional<Todo> byId = todoRepository.findById(todoId);
        Todo todo = byId.orElseThrow(() -> new TodoNotFoundException(todoId));

        if (todo.getMember().getId() == memberId) {
            todoRepository.deleteById(todoId);
        } else {
            throw new MemberNotFoundException(memberId);
        }
        TodoResponseDTO deleted = TodoResponseDTO.builder()
                .todoId(todo.getId())
                .memberId(todo.getMember().getId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .isFinish(todo.getIsFinish())
                .createTime(todo.getCreatedDate())
                .modifiedTime(todo.getModifiedDate())
                .build();

        return deleted;
    }
}
