package com.ssafyss.board_practice.user.domain;

import com.ssafyss.board_practice.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String oauthId;
    private String oauthType;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    private String profileImage;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    public User(
            final String oauthId,
            final String oauthType,
            final String email,
            final String password,
            final String name,
            final String profileImage
    ) {
        this.oauthId = oauthId;
        this.oauthType = oauthType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
    }
}
