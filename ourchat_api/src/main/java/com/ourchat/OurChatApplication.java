package com.ourchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableOpenApi
@SpringBootApplication
public class OurChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(OurChatApplication.class, args);
    }
}
