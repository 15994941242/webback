package com.lin.services;

import com.lin.entity.User;

public interface UserService {

    //查询一个用户
    public User queryOne(String email, String password);

    //新增一个用户
    public int addUser(User user);
}
