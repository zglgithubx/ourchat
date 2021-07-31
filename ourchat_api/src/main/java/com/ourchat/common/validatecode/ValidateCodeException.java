package com.ourchat.common.validatecode;

import org.springframework.security.core.AuthenticationException;

/**
 * 校验异常处理类
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg){
        super(msg);
    }
}
