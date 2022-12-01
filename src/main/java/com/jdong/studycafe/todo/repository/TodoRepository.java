package com.jdong.studycafe.todo.repository;

import com.jdong.studycafe.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t from Todo t where t.member.id=:memberId")
    List<Todo> findByMemberId(Long memberId);
}
