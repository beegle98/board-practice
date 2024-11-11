package com.ssafyss.board_practice.global.dto;

public class BaseResponse {
    private final String message;

    private BaseResponse(Builder builder) {
        this.message = builder.message;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        private String message;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public BaseResponse build() {
            return new BaseResponse(this);
        }
    }
}
