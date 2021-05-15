package com.ourchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class OurChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(OurChatApplication.class, args);
    }
}
