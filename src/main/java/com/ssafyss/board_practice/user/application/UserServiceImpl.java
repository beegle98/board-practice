package com.ssafyss.board_practice.user.application;

import com.ssafyss.board_practice.user.application.exception.UserException;
import com.ssafyss.board_practice.user.domain.User;
import com.ssafyss.board_practice.user.infrastructure.repository.UserRepository;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int idCheck(String userId) throws Exception {
        return userRepository.idCheck(userId);
    }

    @Override
    public User login(String email, String password) {
        try {
            User user = userRepository.login(email);
            System.out.println(user);
            if (user == null) {
                throw new UserException("등록되지 않은 아이디입니다.");
            }

            if (!password.equals(user.getPassword())) {
                throw new UserException("비밀 번호 오류 발생!!!!");
            }
            return user;
        } catch (SQLException e) {
            throw new UserException("로그인 처리 중 오류 발생");
        }
    }

    @Override
    public void join(User user) {
        try {
            User find = userRepository.login(user.getEmail());
            if (find != null) {
                throw new UserException("이미 등록된 아이디 입니다.");
            }
            userRepository.join(user);
        } catch (SQLException e) {
            throw new UserException("회원 정보 처리 중 오류 발생!!!");
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
