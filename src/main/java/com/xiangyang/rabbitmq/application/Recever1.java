package com.xiangyang.rabbitmq.application;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recever1 {
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

			    System.out.println(" [x] Received '" + message + "'");
			    try {
			      try {
					doWork(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    } finally {
			      System.out.println(" [x] Done");
			    }
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
	private static void doWork(String task) throws InterruptedException {
	    for (char ch: task.toCharArray()) {
	        if (ch == '.') Thread.sleep(1000);
	    }
	}
}
