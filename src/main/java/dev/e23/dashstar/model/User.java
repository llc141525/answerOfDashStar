package dev.e23.dashstar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "user";

    public User() {}

    public User(String username, String nickname, String password, String role) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

}
