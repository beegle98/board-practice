package com.ssafyss.board_practice.todo.application;

import com.querydsl.core.BooleanBuilder;
import com.ssafyss.board_practice.todo.domain.QTodo;

public class TodoQueryHelper {

    public static BooleanBuilder createFilterBuilder(Long userId, QTodo todo) {
        BooleanBuilder filterBuilder = new BooleanBuilder();

        addUserFilter(todo, userId, filterBuilder);
        addDeletedFilter(todo, filterBuilder);

        return filterBuilder;
    }

    private static void addUserFilter(QTodo todo, Long userId, BooleanBuilder filterBuilder) {
        filterBuilder.and(todo.userId.eq(userId));
    }

    private static void addDeletedFilter(QTodo todo, BooleanBuilder filterBuilder) {
        filterBuilder.and(todo.deleted.isFalse());
    }

}
