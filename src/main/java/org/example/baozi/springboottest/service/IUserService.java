package org.example.baozi.springboottest.service;

import org.example.baozi.springboottest.pojo.User;
import org.example.baozi.springboottest.pojo.dto.UserDTO;

//? 添加接口是为了添加业务的扩展性、逻辑性
public interface IUserService {
    /**
     * 插入用户
     *
     * @param user --参数
     * @return
     */
    User add(UserDTO user);

    /**
     * 查询用户
     *
     * @param userid 用户id
     * @return
     */
    User getUser(Integer userid);

    /**
     *修改用户
     *
     * @param user
     * @return
     */
    User edit(UserDTO user);

    /**
     * 删除用户
     *
     * @param userid
     * @return
     */
    void deleteUser(Integer userid);
}
