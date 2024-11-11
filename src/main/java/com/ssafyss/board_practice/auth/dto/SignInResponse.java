package com.ssafyss.board_practice.auth.dto;

import com.ssafyss.board_practice.global.response.ResponseDto;

public class SignInResponse implements ResponseDto {
    private Long id;

    public Long getId() {
        return id;
    }

    private SignInResponse(Builder builder) {
        this.id = builder.id;
    }

    public static class Builder {
        private Long id;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public SignInResponse build() {
            return new SignInResponse(this);
        }

    }

}
