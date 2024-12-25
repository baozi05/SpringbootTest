package org.example.baozi.springboottest.service;

import org.example.baozi.springboottest.pojo.User;
import org.example.baozi.springboottest.pojo.dto.UserDTO;
import org.example.baozi.springboottest.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service   //* 配置为spring的bean --- Spring 会将这个类实例化为一个 Bean，并将其注册到 Spring 容器中，便于之后注入
//* 注入可以通过 @Autowired 注解将这个 Bean 注入到其他类中
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(UserDTO user) {

        User userPojo = new User();
        // 此处需要进行拷贝，将 UserDTO拷贝到 User中
        BeanUtils.copyProperties(user,userPojo);

        // 调用数据访问类
        return userRepository.save(userPojo); // 因为在 UserRepository 继承的CRUD接口内指定了操作的对象为 User，所以此处不能直接传入 user(userDTO)
        //* save 可以判断当前数据库的状态---1、id存在--update  2、id不存在--add

    }
    @Override
    public User getUser(Integer userid) {

        // 需要处理id为空指针的情况  orElseThrow 是没有查询到就抛出... 有点类似于 c++的 ? :

        return  userRepository.findById(userid).orElseThrow(()->{
            throw new IllegalArgumentException("用户不存在");
        });

    }

    @Override
    public User edit(UserDTO user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user,userPojo);
        // 同添加
        return userRepository.save(userPojo);
    }

    @Override
    public void deleteUser(Integer userid) {
        userRepository.deleteById(userid);
    }


}
