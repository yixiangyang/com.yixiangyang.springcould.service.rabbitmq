package com.xiangyang.rabbitmq.application;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 根据主题来接收消息
 * @author 15138
 *
 */
public class ReceverLogsTopic {
	private static final String EXCHANGE_NAME="topic_logs"; 
	public static void main(String[] args) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		Channel channel = con.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
	    String queueName = channel.queueDeclare().getQueue();
	    //绑定这边可以用* 或者#来匹配 * 可以替代一个字 # 可以替换零个或者多个单词
	    channel.queueBind(queueName, EXCHANGE_NAME, "*.usd.#");

	    Consumer consumer = new DefaultConsumer(channel) {
	      @Override
	      public void handleDelivery(String consumerTag, Envelope envelope,
	                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
	        String message = new String(body, "UTF-8");
	        System.out.println(" Received '" + envelope.getRoutingKey() + "':'" + message + "'");
	      }
	    };
	    channel.basicConsume(queueName, true, consumer);
	}

}
