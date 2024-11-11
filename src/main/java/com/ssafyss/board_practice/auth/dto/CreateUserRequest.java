package com.ssafyss.board_practice.auth.dto;

public class CreateUserRequest {
    private String email;

    private String password;

    public CreateUserRequest() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
