package com.ssafyss.board_practice.global.exception;

import com.ssafyss.board_practice.global.message.ExceptionMessage;

public class SandBoxException extends RuntimeException {

    public SandBoxException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
