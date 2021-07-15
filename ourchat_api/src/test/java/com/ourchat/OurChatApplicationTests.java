package com.ourchat;

import com.ourchat.common.email.MailServiceImpl;
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
        System.out.println(service.searchFriend("交际花"));

    }
}
