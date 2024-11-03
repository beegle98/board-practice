package com.ssafyss.board_practice.todo.presentation;

import com.ssafyss.board_practice.todo.application.TodoService;
import com.ssafyss.board_practice.todo.application.dto.CreateTodoDto;
import com.ssafyss.board_practice.todo.application.dto.PagingByCursorDto;
import com.ssafyss.board_practice.todo.application.dto.PagingByOffsetDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.presentation.dto.request.CreateTodoRequest;
import com.ssafyss.board_practice.todo.presentation.dto.response.ReadTodoResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class PagingController {
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    private TodoService todoService;

    @Autowired
    public PagingController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 테스트용 임시 유저 아이디
    private final long tempUserId = 5L;

    @GetMapping("/paging/offset")
    public ResponseEntity<ReadTodoResponse> readTodosByOffset(
            @RequestParam int size,
            @RequestParam int page
    ) {
        PagingByOffsetDto pagingByOffsetDto = new PagingByOffsetDto(tempUserId, page, size);
        List<ReadTodoDto> readTodos = todoService.readTodosByOffset(pagingByOffsetDto);
        ReadTodoResponse response = ReadTodoResponse.from(readTodos);
        log.info("readTodosByOffset : {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paging/cursor")
    public ResponseEntity<ReadTodoResponse> readTodosByCursor(
            @RequestParam int size,
            @RequestParam Long id
    ) {
        PagingByCursorDto pagingByCursorDto = new PagingByCursorDto(tempUserId, id, size);
        List<ReadTodoDto> readTodos = todoService.readTodosByCursor(pagingByCursorDto);
        ReadTodoResponse response = ReadTodoResponse.from(readTodos);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/make")
    public ResponseEntity<Void> createTodos(@RequestBody List<CreateTodoRequest> createTodoRequests) {
        List<CreateTodoDto> createTodos = createTodoRequests.stream()
                .map(request -> new CreateTodoDto(tempUserId, request.content()))
                .toList();
        createTodos.forEach(todoService::createTodo);
        return ResponseEntity.ok().build();
    }
}
