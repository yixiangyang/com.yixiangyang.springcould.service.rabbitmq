package com.xiangyang.rabbitmq.application;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender {
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws Exception {
		//获取连接
		Connection con = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = con.createChannel();
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME, null, "发送消息22".getBytes());
		channel.close();
		con.close();
	}

}
