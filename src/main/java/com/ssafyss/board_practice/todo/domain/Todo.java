package com.ssafyss.board_practice.todo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "Todo")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Getter
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "lastModifiedAt")
    private Timestamp lastModifiedAt;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "deleted")
    private boolean deleted;

    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.deleted = true;
    }

}
