package com.ssafyss.board_practice.user.infrastructure;

import com.ssafyss.board_practice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    int countByEmail(String email);

    User findByEmail(String email);
}
