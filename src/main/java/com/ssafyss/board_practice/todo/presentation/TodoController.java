package com.ssafyss.board_practice.todo.presentation;

import com.ssafyss.board_practice.todo.application.TodoService;
import com.ssafyss.board_practice.todo.application.dto.CreateTodoDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.presentation.dto.request.CreateTodoRequest;
import com.ssafyss.board_practice.todo.presentation.dto.response.CreateTodoResponse;
import com.ssafyss.board_practice.todo.presentation.dto.response.ReadTodoResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 로그인 정보를 세션에 저장할 지 토큰으로 할 지 모르겠어서 일단 임시로 UserId 하드코딩
    // 그리고 애초에 샌드박스에 로그인 기능이 없어서 하려면 따로 테스트 해야된다.
    private final long tempUserId = 5L;

    @GetMapping(value = "")
    public ResponseEntity<ReadTodoResponse> readAllTodo(
    ) {
        final List<ReadTodoDto> readAllTodos = todoService.readAllTodos(tempUserId);
        final ReadTodoResponse response = ReadTodoResponse.from(readAllTodos);
        log.info("readAllTodo : {}", response.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "")
    public ResponseEntity<CreateTodoResponse> createTodo(
            @RequestBody CreateTodoRequest request
    ) {
        final CreateTodoDto createTodoDto = CreateTodoDto.of(tempUserId, request);
        final ReadTodoDto readTodoDto = todoService.createTodo(createTodoDto);
        final CreateTodoResponse response = CreateTodoResponse.of(readTodoDto);
        log.info("createTodo : {}", response.toString());
        return ResponseEntity.ok(response);
    }

    @PatchMapping(value = "/{todoId}")
    public ResponseEntity<Void> updateTodo(
            @PathVariable(value = "todoId") Long todoId
    ) {
        todoService.updateTodo(tempUserId, todoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable(value = "todoId") Long todoId
    ) {
        todoService.deleteTodo(tempUserId, todoId);
        return ResponseEntity.noContent().build();
    }
}