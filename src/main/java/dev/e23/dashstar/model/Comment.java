package dev.e23.dashstar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "article_id", insertable = false, updatable = false)
    private Integer articleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    public Comment() {}

    public Comment(String content) {
        this.content = content;
    }
}
