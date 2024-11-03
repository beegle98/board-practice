package com.ssafyss.board_practice.user.application.exception;

import com.ssafyss.board_practice.global.exception.SandBoxException;
import com.ssafyss.board_practice.global.message.ExceptionMessage;

public class NotFoundUserException extends SandBoxException {
    public NotFoundUserException() {
        super(ExceptionMessage.NOT_FOUND_USER);
    }
}
