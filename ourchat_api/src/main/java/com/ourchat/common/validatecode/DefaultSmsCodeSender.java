package com.ourchat.common.validatecode;

import com.ourchat.common.email.MailServiceImpl;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 验证码发送器
 */
@Component
public class DefaultSmsCodeSender {
    @Autowired
    private MailServiceImpl mailService;
    public void send(String email, String code){
        String content="【OurChat】验证码：【"+code+"】（登录验证码，请完成验证），一分钟内有效，如非本人操作，请忽略本消息。";
        try {
            mailService.sendSimpleMail(email,"【OurChat】",content);
        }catch (Exception ex){
            throw ex;
        }
        System.out.println("发送验证成功，邮箱地址为："+email+",发送短信验证码"+code);
    }
}
