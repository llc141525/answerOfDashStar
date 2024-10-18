package dev.e23.dashstar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "articles")  // 表示这个实体类对应的数据库表名是 articles
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne  // 表示这个字段是多对一的关系，即一篇文章只能属于一个用户
    @JoinColumn(name = "author_id")  // 表示这个字段对应的数据库表中的列名是 author_id
    private User author;  // 表示这个字段对应的实体类是 User，实际上存储进数据库的是 User 的 id

    @Column(name = "author_id", insertable = false, updatable = false)
    private Integer authorId;

    public Article() {}

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
