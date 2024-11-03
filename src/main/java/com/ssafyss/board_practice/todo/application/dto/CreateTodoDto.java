package com.ssafyss.board_practice.todo.application.dto;

import com.ssafyss.board_practice.todo.presentation.dto.request.CreateTodoRequest;

public record CreateTodoDto(
        Long userId,
        String content
) {

    public static CreateTodoDto of(final Long userId, final CreateTodoRequest request) {
        return new CreateTodoDto(userId, request.content());
    }
}
