package com.ourchat.system.signup.controller;

import com.ourchat.system.login.entity.Customer;
import com.ourchat.system.login.mapper.CustomerMapper;
import com.ourchat.system.signup.entiy.CustomerDTO;
import com.ourchat.system.signup.service.SignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api(tags = "登录API")
@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @ApiOperation("注册账号")
    @PostMapping("/sign-up")
    public String upLoad(@RequestParam("file")MultipartFile file, CustomerDTO customerDTO){
        if(file.isEmpty()){
            return "文件为空";
        }
        String fileName= file.getOriginalFilename();
        File dest=new File(new File("src/main/resources/customer/icon").getAbsolutePath()+"/"+fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
            if(signUpService.create(customerDTO,dest.getAbsolutePath())){
                return dest.getAbsolutePath();
            }
            return "保存文件成功，注册失败";
        } catch (Exception e) {
            e.printStackTrace();
            return "保存失败";
        }
    }

}
