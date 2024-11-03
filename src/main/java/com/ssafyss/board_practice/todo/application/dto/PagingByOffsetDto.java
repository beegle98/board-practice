package com.ssafyss.board_practice.todo.application.dto;


public record PagingByOffsetDto(
        Long userId,
        int size,
        int page
) {

    public static PagingByOffsetDto of(Long userId, int size, int page) {
        return new PagingByOffsetDto(userId, size, page);
    }

}
