package com.ourchat.common.validatecode;

import org.springframework.stereotype.Component;

/**
 * 验证码发送器
 */
@Component
public class DefaultSmsCodeSender {
    public void send(String email, String code) {
        System.out.println("邮箱地址为："+email+",发送短信验证码"+code);
    }
}
