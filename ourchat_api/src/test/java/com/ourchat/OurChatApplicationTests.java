package com.ourchat;

import com.ourchat.common.email.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OurChatApplicationTests {
    @Autowired
    private MailServiceImpl mailService;

    @Test
    void contextLoads() {
        try {
            mailService.sendSimpleMail("2066035486@qq.com","OurChat","这周日你有空吗?   来自：新乡交际花");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
