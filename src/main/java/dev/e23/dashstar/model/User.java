package dev.e23.dashstar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data  // 表示这个类需要被 Lombok 处理，Lombok 会自动生成 getter、setter、toString、equals、hashCode 等方法
@Entity  // 表示这是一个实体类，会被 Hibernate 管理
@Table(name = "users")  // 表示这个实体类对应的数据库表名是 users
public class User implements Serializable {

    @Id  // 表示这个字段是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 表示主键生成策略是自增
    private Integer id;

    @Column(name = "username", unique = true)  // 表示这个字段对应的数据库表中的列名是 username，并且这个字段是唯一的
    private String username;

    @Column(name = "nickname")  // 表示这个字段对应的数据库表中的列名是 nickname
    private String nickname;

    //     附加题：取消密码显示
    // 出于安全性考虑, 后端通常不会把密码传送到前端, 但是不能直接使用 @JsonIgnore, 因为这样会导致原本的注册登录功能出错.
    // 因此我们应该同意密码的反序列化而阻止它的序列化, 简单的说就是不上传密码但是能接收密码
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")  // 表示这个字段对应的数据库表中的列名是 password
    private String password;

    @Column(name = "role")  // 表示这个字段对应的数据库表中的列名是 role
    private String role = "user";  // 表示这个字段的默认值是 user

}
