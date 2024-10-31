package com.ssafyss.board_practice.global.response;

import lombok.Builder;

// 레코드 모범예시
@Builder
public record ResponseRecord(String msg) implements ResponseDto {
}
