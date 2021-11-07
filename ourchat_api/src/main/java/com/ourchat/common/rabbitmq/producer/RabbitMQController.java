package com.ourchat.common.rabbitmq.producer;

import com.ourchat.common.rabbitmq.producer.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName RabbitMQController
 * @Description TODO
 * @Author ZhuGuangLiang
 * @Date 2021/11/06 22:24
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class RabbitMQController {
    @Resource
    private RabbitMQService rabbitMQService;

    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        log.info("接收参数msg："+msg);
        return rabbitMQService.sendMsg(msg);
    }
    @GetMapping("/publish")
    public String publish(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsgByFanoutExchange(msg);
    }
}
