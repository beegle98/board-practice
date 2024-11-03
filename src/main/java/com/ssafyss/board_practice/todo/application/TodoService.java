package com.ssafyss.board_practice.todo.application;

import com.ssafyss.board_practice.todo.application.dto.CreateTodoDto;
import com.ssafyss.board_practice.todo.application.dto.PagingByCursorDto;
import com.ssafyss.board_practice.todo.application.dto.PagingByOffsetDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.application.exception.ForbiddenUserToUpdateTodoException;
import com.ssafyss.board_practice.todo.application.exception.NotFoundTodoException;
import com.ssafyss.board_practice.todo.domain.Todo;
import com.ssafyss.board_practice.todo.infrastructure.repository.TodoRepository;
import com.ssafyss.board_practice.todo.presentation.TodoController;
import com.ssafyss.board_practice.user.application.exception.NotFoundUserException;
import com.ssafyss.board_practice.user.domain.User;
import com.ssafyss.board_practice.user.infrastructure.repository.UserRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TodoService {

    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<ReadTodoDto> readAllTodos(Long userId) {
        isValidUser(userId);
        final List<Todo> readTodos = todoRepository.findAllByUserIdAndDeletedFalse(userId);
        return readTodos.stream()
                .map(ReadTodoDto::of)
                .toList();
    }

    private User isValidUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
    }

    public ReadTodoDto createTodo(CreateTodoDto createTodoDto) {
        final User user = isValidUser(createTodoDto.userId());
        final Long todoId = saveTodo(user, createTodoDto.content());
        final Todo createdTodo = todoRepository.findById(todoId)
                .orElseThrow(NotFoundTodoException::new);
        return ReadTodoDto.of(createdTodo);
    }

    private Long saveTodo(User user, String content) {
        final Todo todo = Todo.builder()
                .user(user)
                .content(content)
                .build();
        final Todo savedTodo = todoRepository.save(todo);
        return savedTodo.getId();
    }

    public void updateTodo(Long userId, Long todoId) {
        isValidUser(userId);
        final Todo todo = todoRepository.findById(todoId).orElseThrow(NotFoundTodoException::new);
        isValidUserToUpdate(userId, todo.getUser().getId());
        todo.updateCompleted();
    }

    private void isValidUserToUpdate(Long userId, Long todoUserId) {
        if (!userId.equals(todoUserId)) {
            throw new ForbiddenUserToUpdateTodoException();
        }
    }

    public void deleteTodo(Long userId, Long todoId) {
        isValidUser(userId);
        final Todo todo = todoRepository.findById(todoId).orElseThrow(NotFoundTodoException::new);
        isValidUserToUpdate(userId, todo.getUser().getId());
        todo.updateDeleted();
    }

    public List<ReadTodoDto> readTodosByOffset(PagingByOffsetDto pagingByOffsetDto) {
        Pageable pageable = PageRequest.of(pagingByOffsetDto.page(), pagingByOffsetDto.size());
        List<Todo> todos = todoRepository.findByUserIdOrderByIdAsc(pagingByOffsetDto.userId(), pageable);
        List<ReadTodoDto> result = todos.stream()
                .map(ReadTodoDto::of)
                .toList();

        return result;
    }

    public List<ReadTodoDto> readTodosByCursor(PagingByCursorDto pagingByCursorDto) {
        Pageable pageable = PageRequest.of(0, pagingByCursorDto.size(), Sort.by("id").ascending());
        List<Todo> todos = todoRepository.findByUserIdAndIdGreaterThanOrderByIdAsc(pagingByCursorDto.userId(),
                pagingByCursorDto.id(), pageable);
        List<ReadTodoDto> result = todos.stream()
                .map(ReadTodoDto::of)
                .toList();

        return result;
    }
}
