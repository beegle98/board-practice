package com.ssafyss.board_practice.todo.domain;

import com.ssafyss.board_practice.global.entity.BaseTimeEntity;
import com.ssafyss.board_practice.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString(exclude = {"user"})
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "fk_todo_user"), nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String content;

    @Column(nullable = false)
    private boolean completed = false;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    public Todo(final User user, final String content) {
        this.user = user;
        this.content = content;
    }

    public void updateCompleted() {
        this.completed = !this.isCompleted();
    }

    public void updateDeleted() {
        this.deleted = true;
    }
}


