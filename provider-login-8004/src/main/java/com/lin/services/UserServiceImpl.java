package com.lin.services;

import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryOne(String email, String password) {
        User userDB = userMapper.queryOne(email, password);
        if (userDB != null) {
            return userDB;
        }
        throw new RuntimeException("登录失败~");
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

}
