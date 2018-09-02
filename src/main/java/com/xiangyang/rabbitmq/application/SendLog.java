package com.xiangyang.rabbitmq.application;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发送日志，采用的是发布/订阅的方式
 * 消费者如果后启动 ，是不会接收到消息的，消息内容已经丢失，因为没有声明队列，只声明了交换机
 * 当消费者没有队列绑定到交换机，信息会丢失，相当于他认为没有消费者在听，可以随意的将该消息丢弃
 * @author 伊向阳
 *
 */
public class SendLog {
	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		Channel channel = con.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

	    String message = "这个是日志信息";

	    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
	    System.out.println(" Sent " + message + "");
	    channel.close();
	    con.close();
	}

}
