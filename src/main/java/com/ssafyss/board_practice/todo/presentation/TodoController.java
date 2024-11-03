package com.ssafyss.board_practice.todo.presentation;

import com.ssafyss.board_practice.todo.application.TodoService;
import com.ssafyss.board_practice.todo.application.dto.CreateTodoDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDetailDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.presentation.dto.request.CreateTodoRequest;
import com.ssafyss.board_practice.todo.presentation.dto.response.CreateTodoResponse;
import com.ssafyss.board_practice.todo.presentation.dto.response.ReadTodoResponse;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping(value = "")
    public ResponseEntity<ReadTodoResponse> readAllTodo(
            final HttpSession session
    ) {
//        final User user = (User) session.getAttribute("userInfo");
        final List<ReadTodoDto> readAllTodos = todoService.readAllTodos(1L);
        final ReadTodoResponse response = ReadTodoResponse.from(readAllTodos);
        log.info(response.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "")
    public ResponseEntity<CreateTodoResponse> createTodo(
            @RequestBody final CreateTodoRequest request,
            final HttpSession session
    ) {
//        final User user = (User) session.getAttribute("userInfo");
        final CreateTodoDto createTodoDto = CreateTodoDto.of(1L, request);
        final ReadTodoDetailDto todo = todoService.createTodo(createTodoDto);
        final CreateTodoResponse response = CreateTodoResponse.of(todo);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(value = "/{todoId}")
    public ResponseEntity<Void> updateTodo(
            @PathVariable(value = "todoId") Long todoId,
            final HttpSession session
    ) {
//        final User user = (User) session.getAttribute("userInfo");
        todoService.updateTodo(1L, todoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable(value = "todoId") Long todoId,
            final HttpSession session
    ) {
//        final User user = (User) session.getAttribute("userInfo");
        todoService.deleteTodo(1L, todoId);
        return ResponseEntity.noContent().build();
    }
}