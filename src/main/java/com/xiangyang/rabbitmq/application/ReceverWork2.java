package com.xiangyang.rabbitmq.application;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceverWork2 {
	private static final String QUEUE_NAME = "task_queue";

	public static void main(String[] args) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = con.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//prefetchCount 会告诉RabbitMQ不要同时给一个消费者推送多于N个消息，即一旦有N个消息还没有ack，则该consumer将block掉，直到有消息ack
		channel.basicQos(1);
		final Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				//向生产者确认该消息已经被消费，如果不想生产者确认,
				//该消息就一直是 Unacked 状态， 同时rabbitMq消费并没有超时的限制，这也就代表了只要程序不重启 消息就一直会只unacked状态
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume(QUEUE_NAME, false, consumer);

	}

}
