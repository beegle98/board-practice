package com.ssafyss.board_practice.todo.presentation.dto.response;

import com.ssafyss.board_practice.global.response.ResponseDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDto;
import java.util.List;

public record ReadTodoResponse(List<TodoInfoResponse> todos) implements ResponseDto {

    public static ReadTodoResponse from(final List<ReadTodoDto> readTodoDtos) {
        final List<TodoInfoResponse> response = readTodoDtos.stream()
                                                            .map(TodoInfoResponse::from)
                                                            .toList();
        return new ReadTodoResponse(response);
    }

    public record TodoInfoResponse(
            Long id,
            String content,
            boolean completed
    ) {

        public static TodoInfoResponse from(final ReadTodoDto readTodoDto) {
            return new TodoInfoResponse(
                    readTodoDto.id(),
                    readTodoDto.content(),
                    readTodoDto.completed()
            );
        }
    }
}
