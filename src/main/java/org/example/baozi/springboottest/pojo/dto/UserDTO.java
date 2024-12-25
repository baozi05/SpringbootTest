package org.example.baozi.springboottest.pojo.dto;

import org.example.baozi.springboottest.pojo.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    /*
    DTO类的作用：
    用于传输数据，添加需要新增的字段
    避免暴露数据库内部实现
    用于数据传输的对象,通常用于封装数据，以便在网络请求和响应中传输。
     */

    private Integer userId; // 加入id方便修改

    // 因为主键一般是自动生成且业务数据一般不包括主键，所以DTO类内不需要加上主键

    // * 此时可以使用 validation 库内的注解来规定各个属性的性质
    @NotBlank(message = "用户名不能为空")// 去除空格，不能为只含空格的字符串
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Size(min=6,max=12,message = "密码长度需要在6-12位") // 规定字符长度
    // 规范检测会从上到下执行
    private String password;
    @Email(message = "email格式不正确")
    private String email;

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
