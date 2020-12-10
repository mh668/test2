package com.atguigu.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkProducer {

    static final String QUEUE_NAME = "work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("192.168.74.128");

        connectionFactory.setPort(5672);

        connectionFactory.setVirtualHost("/fengge");

        connectionFactory.setUsername("mh555");

        connectionFactory.setPassword("232913qq");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        for (int i = 0; i < 5000; i++) {

            String message = "你好；小兔子！"+i;

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("已发送消息：" + message);

        }


        channel.close();
        connection.close();

    }

}
