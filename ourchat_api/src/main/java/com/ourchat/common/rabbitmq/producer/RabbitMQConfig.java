package com.ourchat.common.rabbitmq.producer;

/**
 * @ClassName RabbitMQConfig
 * @Description TODO
 * @Author ZhuGuangLiang
 * @Date 2021/11/06 21:59
 */
public class RabbitMQConfig {
    //RabbitMq的队列名称
    public static final String RABBITMQ_DEMO_TOPIC="rabbitmqDemoTopic";
    //RabbitMQ的DIRECT交换机名称
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE="rabbitmqDemoDirectExchange";
    //RabbitMQ的DIRECT和队列绑定的匹配键DirectRouting
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING="rabbitmqDemoDirectRouting";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 A 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout.A";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 B 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout.B";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的名称
     */
    public static final String FANOUT_EXCHANGE_DEMO_NAME = "fanout.exchange.demo.name";



}
