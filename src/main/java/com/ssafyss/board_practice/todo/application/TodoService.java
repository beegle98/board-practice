package com.ssafyss.board_practice.todo.application;

import com.ssafyss.board_practice.todo.dto.CreateTodoRequest;
import com.ssafyss.board_practice.todo.dto.DeleteTodoRequest;
import com.ssafyss.board_practice.todo.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.dto.ReadTodoRequest;
import com.ssafyss.board_practice.todo.dto.UpdateTodoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {
    void create(CreateTodoRequest request);

    Page<ReadTodoDto> read(ReadTodoRequest request, Pageable pageable);

    void delete(DeleteTodoRequest request);

    void updateContent(UpdateTodoRequest request);
}
