package com.ssafyss.board_practice.domain.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ssafyss.board_practice.auth.application.AuthServiceImpl;
import com.ssafyss.board_practice.auth.constants.ErrorMessages;
import com.ssafyss.board_practice.auth.dto.SignInResponse;
import com.ssafyss.board_practice.auth.exception.DuplicatedEmailException;
import com.ssafyss.board_practice.auth.exception.SignInFailedException;
import com.ssafyss.board_practice.user.domain.User;
import com.ssafyss.board_practice.user.infrastructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private AuthServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);
        userService = new AuthServiceImpl(userRepository, encoder);
    }

    @Test
    @DisplayName("이메일 중복확인 테스트 : 중복된 이메일이 있을 땐 예외를 반환한다")
    public void checkEmailDuplicatedEmailTest() {
        // given
        String email = "DUPLICATED_EMAIL";
        when(userRepository.countByEmail(email)).thenReturn(1); // 중복된 이메일이 있다고 설정

        // when, then
        DuplicatedEmailException thrown =
                assertThrows(DuplicatedEmailException.class, () -> userService.checkEmail(email));

        // then
        assertEquals(ErrorMessages.EMAIL_DUPLICATED, thrown.getMessage());
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    public void signUpSuccessTest() {
        // given
        String email = "NOT_EXIST_EMAIL";
        String password = "password123";
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        when(userRepository.countByEmail(email)).thenReturn(0);
        when(encoder.encode(password)).thenReturn("hashedPassword");

        // when
        userService.signUp(email, password);
    }

    @Test
    @DisplayName("회원가입 테스트 : 중복된 이메일이 있을 땐 예외를 반환한다")
    public void testSignUpDuplicatedEmail() {
        // given
        String email = "DUPLICATED_EMAIL";
        String password = "password123";
        when(userRepository.countByEmail(email)).thenReturn(1);

        // when, then
        DuplicatedEmailException thrown =
                assertThrows(DuplicatedEmailException.class, () -> userService.signUp(email, password));

        // then
        assertEquals(ErrorMessages.EMAIL_DUPLICATED, thrown.getMessage());
    }


    @Test
    @DisplayName("로그인 성공 테스트")
    public void testSignInSuccess() {
        // given
        String email = "test@example.com";
        String password = "password123";
        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        // when
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encoder.matches(password, user.getPassword())).thenReturn(true);

        // then
        SignInResponse response = userService.signIn("test@example.com", password);
        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
    }

    @Test
    @DisplayName("유저를 찾지 못했을 땐 로그인에 실패한다")
    public void testSignInFailedUserNotFound() {
        // given
        String email = "test@example.com";
        String password = "password123";

        // when
        when(userRepository.findByEmail(email)).thenReturn(null);

        // then
        SignInFailedException exception = assertThrows(SignInFailedException.class,
                () -> userService.signIn(email, password));
        assertEquals(ErrorMessages.SIGN_IN_FAILED, exception.getMessage());
    }

    @Test
    @DisplayName("비밀번호가 틀렸을 땐 로그인에 실패한다")
    public void testSignInFailedWrongPassword() {
        // given
        String email = "test@example.com";
        String password = "password123";
        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        // when
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encoder.matches(password, user.getPassword())).thenReturn(false);

        // then
        SignInFailedException exception = assertThrows(SignInFailedException.class,
                () -> userService.signIn(email, password));
        assertEquals(ErrorMessages.SIGN_IN_FAILED, exception.getMessage());
    }

}
