package com.ssafyss.board_practice.user.infrastructure.repository;

import com.ssafyss.board_practice.user.domain.User;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    User idSearch(String email) throws SQLException;

    void join(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;
}
