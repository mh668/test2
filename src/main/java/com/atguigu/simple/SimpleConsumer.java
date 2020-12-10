package com.atguigu.simple;

import com.atguigu.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class SimpleConsumer {

    static final String QUEUE_NAME = "simple_queue";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        final Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        //创建消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("接收到的消息为：" + new String(body, "utf-8"));

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        //监听消息
        channel.basicConsume(QUEUE_NAME,false,consumer);


    }

}
