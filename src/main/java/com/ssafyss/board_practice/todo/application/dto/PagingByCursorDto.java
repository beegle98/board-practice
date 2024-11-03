package com.ssafyss.board_practice.todo.application.dto;


public record PagingByCursorDto(
        Long userId,
        int size,
        Long id
) {

    public static PagingByCursorDto of(Long userId, int size, Long id) {
        return new PagingByCursorDto(userId, size, id);
    }

}
