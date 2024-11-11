package com.ssafyss.board_practice.todo.presentation;

import com.ssafyss.board_practice.global.response.ApiResponse;
import com.ssafyss.board_practice.global.response.ResponseDto;
import com.ssafyss.board_practice.todo.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TodoExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ResponseDto> handleTodoNotFoundException(TodoNotFoundException e) {
        return ApiResponse.builder()
                .message(e.getMessage())
                .build();
    }

}
