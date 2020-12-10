package com.atguigu.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtils {
    public static Connection getConnection() throws Exception {
//1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
//2.设置参数

        connectionFactory.setHost("192.168.74.128");

        connectionFactory.setPort(5672);

        connectionFactory.setVirtualHost("/fengge");

        connectionFactory.setUsername("mh555");

        connectionFactory.setPassword("232913qq");
//3.创建连接
        return
                connectionFactory.newConnection();
    }
}
