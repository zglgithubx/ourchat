package com.ourchat.system.top.controller;

import com.ourchat.system.login.entity.Customer;
import com.ourchat.system.top.service.TopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class TopController {
    @Autowired
    private TopService topService;

    @ApiOperation("搜索好友")
    @PostMapping("/searchFriend")
    public String searchFriend(String criteria){
        Customer customer= topService.searchFriend(criteria);
        return "成功进入";
    }
    @ApiOperation("添加好友")
    @PostMapping("/addFriend")
    public String addFriend(){
        return "成功进入";
    }
    @ApiOperation("搜索")
    @PostMapping("/search")
    public String search(){
        return "成功进入";
    }
}
