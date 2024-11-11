package com.ssafyss.board_practice.todo.dto;

import com.ssafyss.board_practice.global.response.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class ReadTodoResponse implements ResponseDto {
    Page<ReadTodoDto> todos;
}
