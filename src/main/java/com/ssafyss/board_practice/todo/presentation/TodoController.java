package com.ssafyss.board_practice.todo.presentation;

import com.ssafyss.board_practice.global.message.SuccessMessage;
import com.ssafyss.board_practice.global.response.ApiResponse;
import com.ssafyss.board_practice.global.response.ResponseDto;
import com.ssafyss.board_practice.todo.application.TodoService;
import com.ssafyss.board_practice.todo.dto.CreateTodoRequest;
import com.ssafyss.board_practice.todo.dto.DeleteTodoRequest;
import com.ssafyss.board_practice.todo.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.dto.ReadTodoRequest;
import com.ssafyss.board_practice.todo.dto.ReadTodoResponse;
import com.ssafyss.board_practice.todo.dto.UpdateTodoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse create(@RequestBody CreateTodoRequest request) {
        todoService.create(request);

        return ApiResponse.builder()
                .message(SuccessMessage.SUCCESS_TODO_CREATE.getMessage())
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ResponseDto> read(@RequestBody ReadTodoRequest request, Pageable pageable) {
        Page<ReadTodoDto> todos = todoService.read(request, pageable);

        return ApiResponse.builder()
                .data(ReadTodoResponse.builder()
                        .todos(todos)
                        .build())
                .build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse delete(@RequestBody DeleteTodoRequest request) {
        todoService.delete(request);

        return ApiResponse.builder()
                .message(SuccessMessage.SUCCESS_TODO_DELETE.getMessage())
                .build();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updateContent(@RequestBody UpdateTodoRequest request) {
        todoService.updateContent(request);

        return ApiResponse.builder()
                .message(SuccessMessage.SUCCESS_TODO_UPDATE.getMessage())
                .build();
    }

}
