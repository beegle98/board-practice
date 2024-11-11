package com.ssafyss.board_practice.global.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    // USER
    CONFLICT_USER_EMAIL("중복된 이메일입니다"),
    BAD_REQUEST_USER_SIGN_IN("이메일 혹은 비밀번호가 틀립니다"),
    FORBIDDEN_NOT_SIGN_IN("로그인 해야 볼 수 있는 페이지입니다"),
    BAD_REQUEST_PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다"),

    // TODO
    NOT_FOUND_TODO("찾을 수 없는 Todo입니다"),
    BAD_REQUEST_TODO_FIRST_PAGE("첫번째 페이지 입니다"),
    BAD_REQUEST_TODO_LAST_PAGE("마지막 페이지 입니다");

    private String message;
}
