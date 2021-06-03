package com.lin.mapper;

import com.lin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    //查询邮箱是否存在
    public String queryEmail(@Param("email") String email);

    //查询一个用户
    public User queryOne(@Param("email") String email, @Param("password") String password);

    //新增一个用户
    public int addUser(User user);
}
