package com.ourchat.common.rabbitmq.producer;

public interface RabbitMQService {
    public String sendMsg(String msg) throws Exception;
    public String sendMsgByFanoutExchange(String msg);
}
