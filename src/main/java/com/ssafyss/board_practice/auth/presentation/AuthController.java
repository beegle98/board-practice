package com.ssafyss.board_practice.auth.presentation;

import com.ssafyss.board_practice.auth.application.AuthService;
import com.ssafyss.board_practice.auth.dto.CheckEmailRequest;
import com.ssafyss.board_practice.auth.dto.CreateUserRequest;
import com.ssafyss.board_practice.auth.dto.SignInRequest;
import com.ssafyss.board_practice.auth.dto.SignInResponse;
import com.ssafyss.board_practice.global.message.SuccessMessage;
import com.ssafyss.board_practice.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/checkEmail")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse checkEmail(@RequestBody CheckEmailRequest request) {
        authService.checkEmail(request.email());

        return ApiResponse.builder()
                .message(SuccessMessage.AVAILABLE_USER_EMAIL.getMessage())
                .build();
    }

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse signUp(@RequestBody CreateUserRequest request) {
        authService.signUp(request.email(), request.password());
        return ApiResponse.builder()
                .message(SuccessMessage.SUCCESS_SIGN_UP.getMessage())
                .build();
    }

    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse signIn(@RequestBody SignInRequest request) {
        SignInResponse signInResponse = authService.signIn(request.email(), request.password());
        return ApiResponse.builder()
                .data(signInResponse)
                .message(SuccessMessage.SUCCESS_SIGN_IN.getMessage())
                .build();
    }

}
