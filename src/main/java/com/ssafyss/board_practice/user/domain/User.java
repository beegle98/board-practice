package com.ssafyss.board_practice.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "User")
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oauthId")
    private String oauthId;

    @Column(name = "oauthType")
    private String oauthType;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profileImage")
    private String profileImage;

    @Column(name = "createdAt", columnDefinition = "timestamp default now()")
    private Timestamp createdAt;

    @Column(name = "deletedAt")
    private Timestamp deletedAt;

    @Column(name = "deleted")
    private boolean deleted;

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
