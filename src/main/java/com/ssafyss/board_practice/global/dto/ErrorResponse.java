package com.ssafyss.board_practice.global.dto;

public class ErrorResponse {
    private final String message;

    private ErrorResponse(Builder builder) {
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

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
