package com.ssafyss.board_practice.global.response;

import lombok.Builder;

@Builder
public class ApiResponse<T extends ResponseDto> {
    private T data;
    private String message;
}
