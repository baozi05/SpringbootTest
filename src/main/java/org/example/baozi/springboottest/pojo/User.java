package org.example.baozi.springboottest.pojo;

import javax.persistence.*;

// * 这是一个有JPA属性的POJO类

// 需要操作数据库，要映射为数据库的一个表
@Table(name = "tb_user")
@Entity
// 默认为类名当做表名
public class User {
    // 报错是因为没有主键，把Id添加为主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动生成--主键自增
    @Column(name="user_id") // column 用于指定数据库每一列的属性
    private Integer userId; // 因为连接数据库，而Integer的默认值为null，更加符合数据库开发，所以最好用Integer而不用int
    @Column(name="user_name")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


