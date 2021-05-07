package com.ourchat.system.message.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class TestController {

    @PostMapping("/userLogin")
    public String test(@RequestBody Map data){
        System.out.println(data.get("uid"));
        System.out.println(data.get("password"));
        return "hello world";
    }


}
