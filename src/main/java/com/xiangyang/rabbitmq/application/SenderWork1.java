package com.xiangyang.rabbitmq.application;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class SenderWork1 {

	private static final String QUEUE_NAME = "task_queue";
	
	public static void main(String[] args) throws Exception {
		// 获取连接
		Connection con = ConnectionUtil.getConnection();
		// 创建通道
		Channel channel = con.createChannel();
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME,
		        MessageProperties.PERSISTENT_TEXT_PLAIN,
		        "这是工作消息".getBytes("UTF-8"));
		channel.close();
		con.close();
		
		
	}

}
