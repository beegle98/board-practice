package com.ssafyss.board_practice.auth.exception;

public class SignInFailedException extends RuntimeException {
    public SignInFailedException(String message) {
        super(message);
    }
}
