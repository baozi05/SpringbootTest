package org.example.baozi.springboottest.controller;

import org.example.baozi.springboottest.pojo.User;
import org.example.baozi.springboottest.pojo.ResponseMessage;
import org.example.baozi.springboottest.pojo.dto.UserDTO;
import org.example.baozi.springboottest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController// 转换成 JSON 文本--对比Controller注解更简洁，无需在每一行代码前加@ResponseBody注解
@RequestMapping("/user") //映射 HTTP 请求到处理方法--- 前端请求地址会变为 localhost:8080/user/**

public class UserController {

        @Autowired
        IUserService userService; // 接口接收类


        //* 增加
        @PostMapping    // URL localhost:8080/user  method：post
        // * request代表 注解接收客户端发送的 JSON 数据，并将其转换为 User
        public ResponseMessage<User> addUser(@Validated @RequestBody UserDTO user){
                User newUser = userService.add(user);
                return  ResponseMessage.success(newUser);
        }

        //* 查询
        @GetMapping("/{userid}") // URL localhost:8080/user/1  method：get
        // 此处需要在URL中请求携带一个参数 userid 过来，然后在方法里接收
        /*
        此处如果想要 url 中占位符中的 id 值直接赋值到参数 id 中，需要保证 url 中的参数和方法接收参数一致
        即在URL中请求的参数需要与方法中的参数名相同
        如果不一致的话，其实也可以解决，需要用@PathVariable中的 value 属性来指定对应关系
        比如在URL中请求的是 idd ，则@PathVariable(value = "idd")
         */
        public ResponseMessage<User> getUser(@PathVariable Integer userid){
                User newUser=userService.getUser(userid);
                return  ResponseMessage.success(newUser);
        }

        // 修改
        @PutMapping
        public ResponseMessage<User> modUser(@Validated @RequestBody UserDTO user){
                User newUser = userService.edit(user);
                return  ResponseMessage.success(newUser);
        }

        // 删除
        @DeleteMapping("/{userid}")
        public ResponseMessage<User> delUser(@PathVariable Integer userid){
                userService.deleteUser(userid);
                return  ResponseMessage.success(null);
        }

}
