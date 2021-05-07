package com.ourchat.common.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //设置RabbitMQ地址
        factory.setHost("localhost");
        //建立代理服务器到连接
        Connection connection=factory.newConnection();
        //获得信道
        Channel channel=connection.createChannel();
        //声明交换器
        String exchangeName="hello-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);

        String routingKey="hola";
        //发布消息
        byte[] messageBodyBytes="quit".getBytes();
        channel.basicPublish(exchangeName,routingKey,null,messageBodyBytes);
        channel.close();
        connection.close();
    }
}
