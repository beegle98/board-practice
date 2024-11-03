package com.ssafyss.board_practice.todo.application.dto;

import com.ssafyss.board_practice.todo.domain.Todo;

public record ReadTodoDto(
        Long id,
        Long userId,
        String content,
        boolean completed,
        boolean deleted
) {

    public static ReadTodoDto of(final Todo todo) {
        return new ReadTodoDto(
                todo.getId(),
                todo.getUser().getId(),
                todo.getContent(),
                todo.isCompleted(),
                todo.isDeleted()
        );
    }
}
