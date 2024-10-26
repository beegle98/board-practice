package com.ssafyss.board_practice.user.application;

import com.ssafyss.board_practice.user.domain.User;

public interface UserService {
    User login(String email, String password);

    void join(User user);

    void update(User user);

    void delete(User user);
}
