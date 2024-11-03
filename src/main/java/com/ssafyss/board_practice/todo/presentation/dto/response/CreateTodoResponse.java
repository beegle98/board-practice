package com.ssafyss.board_practice.todo.presentation.dto.response;

import com.ssafyss.board_practice.global.response.ResponseDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDetailDto;

public record CreateTodoResponse(
        Long id,
        String content,
        boolean completed
) implements ResponseDto {

    public static CreateTodoResponse of(final ReadTodoDetailDto dto) {
        return new CreateTodoResponse(
                dto.id(),
                dto.content(),
                dto.completed()
        );
    }
}
