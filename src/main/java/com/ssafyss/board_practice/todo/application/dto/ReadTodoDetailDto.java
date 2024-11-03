package com.ssafyss.board_practice.todo.application.dto;

import com.ssafyss.board_practice.todo.domain.Todo;

public record ReadTodoDetailDto(
        Long id,
        Long userId,
        String content,
        boolean completed,
        boolean deleted
) {

    public static ReadTodoDetailDto from(final Todo todo) {
        return new ReadTodoDetailDto(
                todo.getId(),
                todo.getUser().getId(),
                todo.getContent(),
                todo.isCompleted(),
                todo.isDeleted()
        );
    }
}
