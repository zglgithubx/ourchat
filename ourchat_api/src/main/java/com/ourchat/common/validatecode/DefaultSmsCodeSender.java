package com.ourchat.common.validatecode;

import org.springframework.stereotype.Component;

/**
 * 验证码发送器
 */
@Component
public class DefaultSmsCodeSender {
    public void send(String mobile, String code) {
        System.out.println("向前端"+mobile+"发送短信验证码"+code);
    }
}
