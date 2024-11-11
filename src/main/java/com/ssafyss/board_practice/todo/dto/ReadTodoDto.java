package com.ssafyss.board_practice.todo.dto;

import com.ssafyss.board_practice.global.response.ResponseDto;
import com.ssafyss.board_practice.todo.domain.Todo;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadTodoDto implements ResponseDto {
    private Long id;
    private Long userId;
    private String content;
    private boolean completed;
    private Timestamp lastModifiedAt;

    public static ReadTodoDto from(Todo todo) {
        return ReadTodoDto.builder()
                .id(todo.getId())
                .userId(todo.getUserId())
                .content(todo.getContent())
                .completed(todo.isCompleted())
                .lastModifiedAt(todo.getLastModifiedAt())
                .build();
    }

}
