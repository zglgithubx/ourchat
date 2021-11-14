package com.ourchat.system.login.controller;

import com.ourchat.common.enums.ResultEnum;
import com.ourchat.common.exception.BadRequestException;
import com.ourchat.common.response.Result;
import com.ourchat.common.validatecode.DefaultSmsCodeSender;
import com.ourchat.common.validatecode.SmsCodeGenerator;
import com.ourchat.common.validatecode.ValidateCode;
import com.ourchat.common.validatecode.ValidateCodeException;
import com.ourchat.system.login.mapper.CustomerMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Api(tags = "登录模块")
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final SmsCodeGenerator smsCodeGenerator;
    private final DefaultSmsCodeSender defaultSmsCodeSender;
    private final CustomerMapper customerMapper;
    @ApiOperation("发送验证码")
    @GetMapping("/auth/sms")
    public Result<Void> createSmsCode(HttpSession session, @RequestParam String email){
        if(customerMapper.getCustomerEmail(email)==null){
            throw new BadRequestException(ResultEnum.NO_USER);
        }
        ValidateCode smsCode=smsCodeGenerator.generate();
        session.setAttribute(email,smsCode);
        defaultSmsCodeSender.send(email,smsCode.getCode());
        return Result.success();
    }
}
