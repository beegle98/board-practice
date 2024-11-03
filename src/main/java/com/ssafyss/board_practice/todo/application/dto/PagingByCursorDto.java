package com.ssafyss.board_practice.todo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PagingByCursorDto {
    private long userId;
    private long id;
    private int size;
}
