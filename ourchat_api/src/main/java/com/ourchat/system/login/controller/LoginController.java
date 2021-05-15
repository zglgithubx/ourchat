package com.ourchat.system.login.controller;

import com.ourchat.common.validatecode.DefaultSmsCodeSender;
import com.ourchat.common.validatecode.SmsCodeGenerator;
import com.ourchat.common.validatecode.ValidateCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Api(description = "登录API")
@RestController
public class LoginController {
    @Autowired
    private SmsCodeGenerator smsCodeGenerator;
    @Autowired
    private DefaultSmsCodeSender defaultSmsCodeSender;

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpSession session, @RequestParam String mobile){
        ValidateCode smsCode=smsCodeGenerator.generate(new ServletWebRequest(request));
        session.setAttribute(mobile,smsCode);
        defaultSmsCodeSender.send(mobile,smsCode.getCode());
    }
    @PostMapping("/authentication/mobile")
    public void validateSmsCode(@RequestParam String mobile,@RequestParam String password, @RequestParam String smsCode){
    }
}
