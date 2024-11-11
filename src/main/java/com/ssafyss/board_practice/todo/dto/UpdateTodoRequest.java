package com.ssafyss.board_practice.todo.dto;

import lombok.Getter;

@Getter
public class UpdateTodoRequest {
    private Long id;
    private String content;
}
