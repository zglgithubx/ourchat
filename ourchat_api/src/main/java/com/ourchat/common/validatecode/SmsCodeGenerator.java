package com.ourchat.common.validatecode;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 */
@Component
public class SmsCodeGenerator {
    public ValidateCode generate(ServletWebRequest request) {
//        String code = RandomStringUtils.randomNumeric(4);
        String code="1111";
        return new ValidateCode(code, 60);
    }
}
