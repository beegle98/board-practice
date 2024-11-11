package com.ssafyss.board_practice.auth.presentation;

import com.ssafyss.board_practice.auth.exception.DuplicatedEmailException;
import com.ssafyss.board_practice.auth.exception.SignInFailedException;
import com.ssafyss.board_practice.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(DuplicatedEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleDuplicatedEmailException(DuplicatedEmailException e) {
        return ApiResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(SignInFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse handleSignInFailedException(SignInFailedException e) {
        return ApiResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
