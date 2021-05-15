package com.ourchat;

import com.ourchat.system.login.mapper.UserMapper;
import com.ourchat.system.login.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class OurChatApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
//        Customer customer = userMapper.getUserByPhoneNumber("1");
        System.out.println(redisTemplate);
        System.out.println(userMapper.getUserByPhoneNumber("1"));

    }
}
