package com.lin.controller;

import com.lin.entity.User;
import com.lin.entity.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
    public static final String LOGIN_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        return restTemplate.postForObject(LOGIN_URL+"/login",user,Map.class);
    }

    @GetMapping("/sendMail")
    public Map sentMail(@RequestParam String email){
        return restTemplate.getForObject(LOGIN_URL+"/sendMail?email="+email,Map.class);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserVo userVo) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username", userVo.getUsername());
        requestBody.add("code", userVo.getCode());
        requestBody.add("email", userVo.getEmail());
        requestBody.add("password", userVo.getPassword());
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(requestBody);
        return (Map)restTemplate.postForEntity(LOGIN_URL+"/register", requestEntity,Map.class);
//        return restTemplate.postForObject(LOGIN_URL+"/register",userVo,Map.class);
    }
}
