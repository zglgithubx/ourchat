package com.ourchat.system.message.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class TestController {
    @ApiOperation("测试请求")
    @GetMapping("/userLogin")
    public String test(){
        return "hello world";
    }
}
