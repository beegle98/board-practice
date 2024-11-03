package com.ssafyss.board_practice.todo.infrastructure.repository;

import com.ssafyss.board_practice.todo.domain.Todo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findById(final Long id);

    @Query("""
            SELECT t
            FROM Todo t
            JOIN FETCH t.user tu
            WHERE tu.id = :userId AND t.deleted = FALSE
            ORDER BY t.createdAt
            """)
    List<Todo> findAllByUserIdAndDeletedFalse(final Long userId);
}
