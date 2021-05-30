package com.lin.controller;

import com.lin.entity.User;
import com.lin.entity.UserVo;
import com.lin.services.MailService;
import com.lin.services.UserService;
import com.lin.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String email, @RequestParam String password) {
        Map<String, Object> map = new HashMap<>();
        try {
            User userdb = userService.queryOne(email, password);
            Map<String, String> payload = new HashMap<>();
            payload.put("userId", Integer.toString(userdb.getUserId()));
            payload.put("email", userdb.getEmail());
            //生成令牌
            String token = JWTUtils.getToken(payload);
            map.put("state", true);
            map.put("msg", "认证成功！");
            map.put("token", token);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", "认证失败！");
        }
        return map;
    }

    //发送验证码
    @PostMapping("/sendMail")
    public Map sendMail(@RequestParam String email) {
        Map<String, String> map = mailService.sendMail(email);
        return map;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserVo userVo, HttpServletRequest request) {
        boolean result = mailService.registered(userVo, request);
        Map<String, Object> map = new HashMap<>();
        if (result == true) {
            map.put("state", true);
            map.put("msg", "注册成功！");
        } else {
            map.put("state", false);
            map.put("msg", "注册失败！");
        }
        return map;
    }


}
