package org.example.baozi.springboottest.repository;

import org.example.baozi.springboottest.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
/*
 注册为Spring的bean，类似于业务逻辑层的 @Service，之后可被管理并注入
 而repository 用于数据访问层，表明这个类是操作数据库
 所以他们实际上是 @Component 的扩展，为了标识不同层以及为这些层提供特定方法
 */
//*                                                <需要有数据库操作的类名，该类中主键的类型>
public interface UserRepository extends CrudRepository<User, Integer> {
        // 该接口进行数据库的操作
}
