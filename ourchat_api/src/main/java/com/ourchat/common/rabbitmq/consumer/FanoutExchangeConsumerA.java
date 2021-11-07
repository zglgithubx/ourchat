package com.ourchat.common.rabbitmq.consumer;

import com.ourchat.common.rabbitmq.producer.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName FanoutExchangeConsumerA
 * @Description TODO
 * @Author ZhuGuangLiang
 * @Date 2021/11/07 09:57
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_A))
public class FanoutExchangeConsumerA {

    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("队列A收到消息：" + map.toString());
    }

}
