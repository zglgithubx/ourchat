package com.ourchat.system.login.controller;

import com.ourchat.common.validatecode.DefaultSmsCodeSender;
import com.ourchat.common.validatecode.SmsCodeGenerator;
import com.ourchat.common.validatecode.ValidateCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Api(tags = "登录验证模块")
@RestController
public class LoginController {
    @Autowired
    private SmsCodeGenerator smsCodeGenerator;
    @Autowired
    private DefaultSmsCodeSender defaultSmsCodeSender;
    @ApiOperation("发送验证码")
    @GetMapping("/auth/sms")
    public void createSmsCode(HttpSession session, @RequestParam String email){
        ValidateCode smsCode=smsCodeGenerator.generate();
        session.setAttribute(email,smsCode);
        defaultSmsCodeSender.send(email,smsCode.getCode());
    }
    @ApiOperation("登录验证")
    @PostMapping("/auth/email")
    public void validateSmsCode(@RequestParam String email,@RequestParam String password, @RequestParam String smsCode){
    }
}
