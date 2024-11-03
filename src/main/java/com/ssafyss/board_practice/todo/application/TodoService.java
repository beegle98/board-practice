package com.ssafyss.board_practice.todo.application;

import com.ssafyss.board_practice.todo.application.dto.CreateTodoDto;
import com.ssafyss.board_practice.todo.application.dto.PagingByCursorDto;
import com.ssafyss.board_practice.todo.application.dto.PagingByOffsetDto;
import com.ssafyss.board_practice.todo.application.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.application.exception.ForbiddenUserToUpdateTodoException;
import com.ssafyss.board_practice.todo.application.exception.NotFoundTodoException;
import com.ssafyss.board_practice.todo.domain.Todo;
import com.ssafyss.board_practice.todo.infrastructure.repository.TodoRepository;
import com.ssafyss.board_practice.user.application.exception.NotFoundUserException;
import com.ssafyss.board_practice.user.domain.User;
import com.ssafyss.board_practice.user.infrastructure.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TodoService {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<ReadTodoDto> readAllTodos(final Long userId) {
        isValidUser(userId);
        final List<Todo> readTodos = todoRepository.findAllByUserIdAndDeletedFalse(userId);
        return readTodos.stream()
                .map(ReadTodoDto::of)
                .toList();
    }

    private User isValidUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
    }

    public ReadTodoDto createTodo(final CreateTodoDto createTodoDto) {
        final User user = isValidUser(createTodoDto.userId());
        final Long todoId = saveTodo(user, createTodoDto.content());
        final Todo createdTodo = todoRepository.findById(todoId)
                .orElseThrow(NotFoundTodoException::new);
        return ReadTodoDto.of(createdTodo);
    }

    private Long saveTodo(final User user, final String content) {
        final Todo todo = Todo.builder()
                .user(user)
                .content(content)
                .build();
        final Todo savedTodo = todoRepository.save(todo);
        return savedTodo.getId();
    }

    public void updateTodo(final Long userId, final Long todoId) {
        isValidUser(userId);
        final Todo todo = todoRepository.findById(todoId).orElseThrow(NotFoundTodoException::new);
        isValidUserToUpdate(userId, todo.getUser().getId());
        todo.updateCompleted();
    }

    private void isValidUserToUpdate(final Long userId, final Long todoUserId) {
        if (!userId.equals(todoUserId)) {
            throw new ForbiddenUserToUpdateTodoException();
        }
    }

    public void deleteTodo(final Long userId, final Long todoId) {
        isValidUser(userId);
        final Todo todo = todoRepository.findById(todoId).orElseThrow(NotFoundTodoException::new);
        isValidUserToUpdate(userId, todo.getUser().getId());
        todo.updateDeleted();
    }

    public List<ReadTodoDto> readTodosByOffset(PagingByOffsetDto pagingByOffsetDto) {
        Pageable pageable = PageRequest.of(pagingByOffsetDto.getPage(), pagingByOffsetDto.getSize());
        List<Todo> todos = todoRepository.findByUserIdOrderByIdAsc(pagingByOffsetDto.getUserId(), pageable);
        List<ReadTodoDto> result = todos.stream()
                .map(ReadTodoDto::of)
                .toList();

        return result;
    }

    public List<ReadTodoDto> readTodosByCursor(PagingByCursorDto pagingByCursorDto) {
        Pageable pageable = PageRequest.of(0, pagingByCursorDto.getSize(), Sort.by("id").ascending());
        List<Todo> todos = todoRepository.findByUserIdAndIdGreaterThanOrderByIdAsc(pagingByCursorDto.getUserId(),
                pagingByCursorDto.getId(), pageable);
        List<ReadTodoDto> result = todos.stream()
                .map(ReadTodoDto::of)
                .toList();

        return result;
    }
}
