package com.ourchat.common.validatecode;

import com.ourchat.common.email.MailServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 */
@Component
public class SmsCodeGenerator {
    public ValidateCode generate() {
//        String code = RandomStringUtils.randomNumeric(4);
        String code="54188";
        return new ValidateCode(code, 60);
    }
}



