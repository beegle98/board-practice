package com.ssafyss.board_practice.todo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PagingByOffsetDto {
    private long userId;
    private int page;
    private int size;
}
