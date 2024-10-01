package dev.e23.dashstar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "author_id", insertable = false, updatable = false)
    private Integer authorId;

    public Article() {}

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
