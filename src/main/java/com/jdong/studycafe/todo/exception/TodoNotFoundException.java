package com.jdong.studycafe.todo.exception;

import com.jdong.studycafe.common.error.exception.EntityNotFoundException;
import com.jdong.studycafe.common.error.exception.ErrorCode;

public class TodoNotFoundException extends EntityNotFoundException {


    public TodoNotFoundException(Long todoId) {
        super("todoId: " + todoId.toString() + " is not found", ErrorCode.TODO_NOT_FOUND);
    }
}
