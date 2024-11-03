package com.ssafyss.board_practice.todo.application.exception;

import com.ssafyss.board_practice.global.exception.SandBoxException;
import com.ssafyss.board_practice.global.message.ExceptionMessage;

public class ForbiddenUserToUpdateTodoException extends SandBoxException {

    public ForbiddenUserToUpdateTodoException() {
        super(ExceptionMessage.FORBIDDEN_USER_TO_UPDATE_TODO);
    }
}
