package com.ssafyss.board_practice.global.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse<T extends ResponseDto> {
    private T data;
    private String message;
}
