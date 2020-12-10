package com.atguigu.work;

import com.atguigu.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class WorkConsumer2 {

    static final String QUEUE_NAME = "work_queue";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        final Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.basicQos(1);

        //创建消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("接收到的消息为：" + new String(body, "utf-8"));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        //监听消息
        channel.basicConsume(QUEUE_NAME,false,consumer);


    }

}
