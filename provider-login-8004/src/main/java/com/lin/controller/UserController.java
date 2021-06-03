package com.lin.controller;

import com.lin.entity.User;
import com.lin.entity.UserVo;
import com.lin.services.MailService;
import com.lin.services.UserService;
import com.lin.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private String port;

    @Autowired
    private MailService mailService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        try {
            User userdb = userService.queryOne(user.getEmail(), user.getPassword());
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
    @GetMapping("/sendMail")
    public Map sendMail(@RequestParam String email) {
        Map<String, String> map = mailService.sendMail(email);
        return map;
    }

    @GetMapping("/getHost")
    public String getHost(){
        return "查询成功，port="+port;
    }

//    @PostMapping("/register")
//    public Map<String, Object> register(@RequestBody UserVo userVo) {
//        boolean result = mailService.registered(userVo, request);;
//        Map<String, Object> map = new HashMap<>();
//        if (result == true) {
//            map.put("state", true);
//            map.put("msg", "注册成功！");
//        } else {
//            map.put("state", false);
//            map.put("msg", "注册失败！");
//        }
//        return map;
//    }


    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserVo userVo) {
        boolean result = mailService.registered(userVo);
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
