package com.ourchat;

import com.ourchat.common.email.MailServiceImpl;
import com.ourchat.common.response.Result;
import com.ourchat.system.top.service.TopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OurChatApplicationTests {
    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private TopService service;

    @Test
    void contextLoads() {
        String code="54188";
//        String code = RandomStringUtils.randomNumeric(4);
        String content="【OurChat】验证码："+code+"（登录验证码，请完成验证），一分钟内有效，如非本人操作，请忽略本消息。";
        try {
            mailService.sendSimpleMail("2076202161@qq.com","【OurChat】",content);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("发送验证码失败");
        }
    }

    @Test
    void interfaceTest(){
        System.out.println(Result.success(service.searchFriendName("交际花")));
    }
}
