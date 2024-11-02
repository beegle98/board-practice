package com.ssafyss.board_practice.global.message;

import lombok.Getter;

@Getter
public enum SuccessMessage {

    // USER
    AVAILABLE_USER_EMAIL("사용할 수 있는 이메일입니다"),
    SUCCESS_SIGN_UP("회원가입 성공"),
    SUCCESS_SIGN_IN("로그인 성공"),
    SUCCESS_SIGN_OUT("로그아웃 성공"),
    SUCCESS_USER_PASSWORD_UPDATE("비밀번호 변경 성공"),

    // TODO
    SUCCESS_TODO_CREATE("글 등록 성공"),
    SUCCESS_TODO_UPDATE("글 수정 성공"),
    SUCCESS_TODO_DELETE("글 삭제 성공");

    SuccessMessage(String message) {
        this.message = message;
    }

    private String message;
}
