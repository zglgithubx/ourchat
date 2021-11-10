package com.ourchat.system.top.controller;

import com.ourchat.common.response.Result;
import com.ourchat.system.login.entity.Customer;
import com.ourchat.system.top.service.TopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;
@Api(tags = "公共模块")
@RestController
@RequestMapping("/api/common")
public class TopController {
    @Autowired
    private TopService topService;

    @ApiOperation("搜索好友")
    @PostMapping("/searchFriend")
    public Result<Object> searchFriend(@RequestParam("email") String criteria){
        if (( criteria != null) && (!criteria.isEmpty())) {
            if(Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$",criteria)){
                return Result.success(topService.searchFriend(criteria));
            }else{
                return Result.success(topService.searchFriendName(criteria));
            }
        }
        return Result.error("搜索内容不合法");
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
