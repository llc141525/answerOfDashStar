package dev.e23.dashstar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "comments")  // 表示这个实体类对应的数据库表名是 comments
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @ManyToOne  // 表示这个字段是多对一的关系，即一个评论只能属于一篇文章
    @JoinColumn(name = "article_id")  // 表示这个字段对应的数据库表中的列名是 article_id
    private Article article;  // 表示这个字段对应的实体类是 Article，实际上存储进数据库的是 Article 的 id

    @Column(name = "article_id", insertable = false, updatable = false)
    private Integer articleId;

    @ManyToOne  // 表示这个字段是多对一的关系，即一个评论只能属于一个用户
    @JoinColumn(name = "user_id")  // 表示这个字段对应的数据库表中的列名是 user_id
    private User user;  // 表示这个字段对应的实体类是 User，实际上存储进数据库的是 User 的 id

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    public Comment() {}

    public Comment(String content) {
        this.content = content;
    }
}
