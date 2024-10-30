package com.ssafyss.board_practice.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"})
public class User {
    private long id;
    private String oauthId;
    private String oauthType;
    private String name;
    private String email;
    private String password;
    private String profileImageUrl;
    private String createdAt;
    private String deletedAt;
    private boolean deleted;


}
