package com.lin.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lin.entity.User;
import com.lin.entity.UserVo;
import com.lin.mapper.UserMapper;
import com.lin.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Value("1351503414@qq.com")
    private String from;

    //发送验证码
    public Map<String, String> sendMail(String email) {
        Map<String, String> map = new HashMap<>();
        if (userMapper.queryEmail(email) != null) {     //邮箱已存在
            map.put("state", "existEmail");
            return map;
        }
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //生成随机数
            String code = randomCode();
            helper.setSubject("验证码邮件");//主题
            helper.setText("<strong>尊敬的用户：您好！</strong><br>您正在进行<span style=\"color: red\">" +
                    "注册账号</span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">" + code +
                    "</span>，以完成操作。" +
                    "<p style=\"color:#747474;\">\n" +
                    "注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请忽略此邮件\n" +
                    "<br>（工作人员不会向你索取此验证码，请勿泄漏！)\n" +
                    "</p>\n" +
                    "————————————————\n", true);
            helper.setTo(email);
            helper.setFrom(from);
            mailSender.send(mimeMessage);//发送
            //将验证码保存在codeToken令牌中
            Map<String, String> payload = new HashMap<>();
            payload.put("code", code);
            payload.put("email", email);
            String codeToken = JWTUtils.getToken(payload);
            map.put("codeToken", codeToken);
            map.put("state", "true");
            return map;
        } catch (Exception e) {
            map.put("state", "errorEmail");
//            e.printStackTrace();
            return map;
        }
    }

    public String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    //检验验证码
    public boolean registered(UserVo userVo) {
        String codeToken = request.getHeader("codeToken");
        DecodedJWT verify = JWTUtils.verify(codeToken);
        String email = verify.getClaim("email").asString();
        String code = verify.getClaim("code").asString();

        //获取表单中的内容
        String inputEmail = userVo.getEmail();
        String inputCode = userVo.getCode();

        //进行检验判断
        if (!inputEmail.equals(email)) {
//            System.out.println(email);
//            System.out.println(inputEmail);
            return false;
        } else if (!code.equals(inputCode)) {
//            System.out.println(code);
//            System.out.println(inputCode);
            return false;
        }
        User user = new User();
        user.setUsername(userVo.getUsername());
        user.setEmail(userVo.getEmail());
        user.setPassword(userVo.getPassword());
        userMapper.addUser(user);
        return true;
    }
}
