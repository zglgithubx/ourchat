package com.ourchat.common.validatecode;

import com.ourchat.common.email.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 */
@Component
public class SmsCodeGenerator {
    @Autowired
    private MailServiceImpl mailService;
    public ValidateCode generate(ServletWebRequest request,String email) {
        String code="1111";
//        String code = RandomStringUtils.randomNumeric(4);
        String content="【OurChat】验证码："+code+"（登录验证码，请完成验证），一分钟内有效，如非本人操作，请忽略本消息。";
        try {
            mailService.sendSimpleMail(email,"【OurChat】",content);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("发送验证码失败");
            return null;
        }
        return new ValidateCode(code, 60);
    }
}
