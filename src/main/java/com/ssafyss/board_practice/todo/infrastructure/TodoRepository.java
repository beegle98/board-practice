package com.ssafyss.board_practice.todo.infrastructure;

import com.ssafyss.board_practice.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
