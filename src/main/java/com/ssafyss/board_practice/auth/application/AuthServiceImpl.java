package com.ssafyss.board_practice.auth.application;

import com.ssafyss.board_practice.auth.dto.SignInResponse;
import com.ssafyss.board_practice.auth.exception.DuplicatedEmailException;
import com.ssafyss.board_practice.auth.exception.SignInFailedException;
import com.ssafyss.board_practice.global.message.ExceptionMessage;
import com.ssafyss.board_practice.user.domain.User;
import com.ssafyss.board_practice.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void checkEmail(String email) {
        int count = userRepository.countByEmail(email);
        if (count >= 1) {
            throw new DuplicatedEmailException(ExceptionMessage.CONFLICT_USER_EMAIL.getMessage());
        }
    }

    @Override
    @Transactional
    public void signUp(String email, String password) {
        checkEmail(email);
        // 회원가입 할 때 비밀번호 인코딩 코드 추가
        final String encodedPassword = encoder.encode(password);
        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .build();
        userRepository.save(user);
    }

    @Override
    public SignInResponse signIn(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new SignInFailedException(ExceptionMessage.BAD_REQUEST_USER_SIGN_IN.getMessage());
        }
        return new SignInResponse.Builder()
                .id(user.getId())
                .build();
    }
}
