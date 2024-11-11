package com.ssafyss.board_practice.domain.user.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.ssafyss.board_practice.user.domain.User;
import com.ssafyss.board_practice.user.infrastructure.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원 삽입 확인하는 테스트")
    void insertUserTest() {
        // given
        String email = "test@example.com";
        String password = "password123";
        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        userRepository.save(user);
        // when
        int count = userRepository.countByEmail(email);
        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 이메일일 경우 0을 반환한다")
    void returnZeroWhenNotDuplicatedEmail() {
        // given
        String email = "NOT_EXIST_EMAIL";
        // when
        int count = userRepository.countByEmail(email);
        // then
        assertThat(count).isEqualTo(0);
    }

    @Test
    @DisplayName("존재하는 유저일 경우 해당 User entity를 반환한다")
    void returnUserWhenExist() {
        // given
        String email = "ssafy@ssafy.com";
        // when
        User user = userRepository.findByEmail(email);
        // then
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 유저일 경우 null을 반환한다")
    void returnNullWhenExist() {
        // given
        String email = "NOT_EXIST_EMAIL";
        // when
        User user = userRepository.findByEmail(email);
        // then
        assertThat(user).isNull();
    }

}
