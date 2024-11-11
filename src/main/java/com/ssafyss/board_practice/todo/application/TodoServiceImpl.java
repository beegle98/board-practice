package com.ssafyss.board_practice.todo.application;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafyss.board_practice.global.message.ExceptionMessage;
import com.ssafyss.board_practice.todo.domain.QTodo;
import com.ssafyss.board_practice.todo.domain.Todo;
import com.ssafyss.board_practice.todo.dto.CreateTodoRequest;
import com.ssafyss.board_practice.todo.dto.DeleteTodoRequest;
import com.ssafyss.board_practice.todo.dto.ReadTodoDto;
import com.ssafyss.board_practice.todo.dto.ReadTodoRequest;
import com.ssafyss.board_practice.todo.dto.UpdateTodoRequest;
import com.ssafyss.board_practice.todo.exception.TodoNotFoundException;
import com.ssafyss.board_practice.todo.infrastructure.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional
    public void create(CreateTodoRequest request) {
        Todo todo = Todo.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .build();
        todoRepository.save(todo);
    }

    @Override
    public Page<ReadTodoDto> read(ReadTodoRequest request, Pageable pageable) {
        QTodo todo = QTodo.todo;
        BooleanBuilder filterBuilder = TodoQueryHelper.createFilterBuilder(request.getUserId(), todo);

        long totalCount = queryFactory.selectFrom(todo)
                .where(filterBuilder)
                .fetchCount();

        List<ReadTodoDto> todos = queryFactory.selectFrom(todo)
                .where(filterBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(ReadTodoDto::from)
                .toList();

        return new PageImpl<>(todos, pageable, totalCount);
    }

    @Override
    @Transactional
    public void delete(DeleteTodoRequest request) {
        Todo todo = todoRepository.findById(request.getId())
                .orElseThrow(() -> new TodoNotFoundException(ExceptionMessage.NOT_FOUND_TODO.getMessage()));
        todo.delete();
    }

    @Override
    @Transactional
    public void updateContent(UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(request.getId())
                .orElseThrow(() -> new TodoNotFoundException(ExceptionMessage.NOT_FOUND_TODO.getMessage()));
        todo.updateContent(request.getContent());
    }
}
