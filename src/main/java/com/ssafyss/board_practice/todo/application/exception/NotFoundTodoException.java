package com.ssafyss.board_practice.todo.application.exception;

import com.ssafyss.board_practice.global.exception.SandBoxException;
import com.ssafyss.board_practice.global.message.ExceptionMessage;

public class NotFoundTodoException extends SandBoxException {
    public NotFoundTodoException() {
        super(ExceptionMessage.NOT_FOUND_TODO);
    }
}
