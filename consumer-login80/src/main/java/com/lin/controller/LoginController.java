package com.lin.controller;

import com.lin.entity.User;
import com.lin.entity.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
//    public static final String LOGIN_URL = "http://localhost:8001";
    public static final String LOGIN_URL = "http://LOGIN-PROVIDER-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/getHost")
    public String getHost(){
        return restTemplate.getForObject(LOGIN_URL + "/getHost",String.class);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        return restTemplate.postForObject(LOGIN_URL + "/login", user, Map.class);
    }

    @GetMapping("/sendMail")
    public Map sentMail(@RequestParam String email) {
        return restTemplate.getForObject(LOGIN_URL + "/sendMail?email=" + email, Map.class);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserVo userVo, HttpServletRequest request) {
        return restTemplate.postForObject(LOGIN_URL + "/register", userVo, Map.class);
    }
}
