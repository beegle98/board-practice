package com.ssafyss.board_practice.user.infrastructure.repository;

import com.ssafyss.board_practice.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}
