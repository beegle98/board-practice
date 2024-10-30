package com.ssafyss.board_practice.user.infrastructure.repository;

import com.ssafyss.board_practice.user.domain.User;
import java.sql.SQLException;


public interface UserRepository {

    int idCheck(String username) throws SQLException;

    User login(String email) throws SQLException;

    void join(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;
}
