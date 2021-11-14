package com.ourchat.common.exception;

import com.ourchat.common.enums.ResultEnum;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @ClassName BadRequestException
 * @Author ZhuGuangLiang
 * @Date 2021/11/12 17:49
 */

@Getter
public class BadRequestException extends RuntimeException {

    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(Integer status, String msg) {
        super(msg);
        this.status = status;
    }

    public BadRequestException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.status = resultEnum.getCode();
    }

}

