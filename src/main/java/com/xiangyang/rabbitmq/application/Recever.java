package com.xiangyang.rabbitmq.application;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recever {
	private final static String QUEUE_NAME = "hello1";
	public static void main(String[] args) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = con.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println( message );
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
