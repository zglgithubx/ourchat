package com.ourchat.common.rabbitmq.consumer;

import com.ourchat.common.rabbitmq.producer.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName RabbitDemoConsumer
 * @Description TODO
 * @Author ZhuGuangLiang
 * @Date 2021/11/07 09:19
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC))
public class RabbitDemoConsumer {
    @RabbitHandler
    public void process(Map map){
        System.out.println("消费者从服务端消费消息："+map.toString());
    }
}
