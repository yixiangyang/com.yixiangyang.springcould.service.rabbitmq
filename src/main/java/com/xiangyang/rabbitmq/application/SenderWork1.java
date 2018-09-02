package com.xiangyang.rabbitmq.application;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
/**
 * 工作队列 ，相当月将同一个工作交给一个工作者或者多个工作者去完成
 * 发送消息，消费者会采用竞争消费的形式，
 * 谁消费的速度快，谁就消费的多
 * @author 伊向阳
 *
 */
public class SenderWork1 {

	private static final String QUEUE_NAME = "task_queue";
	
	public static void main(String[] args) throws Exception {
		// 获取连接
		Connection con = ConnectionUtil.getConnection();
		// 创建通道
		Channel channel = con.createChannel();
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		for(int i =0;i<100;i++){
			String message = "这个是工作.消息"+i;
			channel.basicPublish("", QUEUE_NAME,
			        MessageProperties.PERSISTENT_TEXT_PLAIN,
			        message.getBytes("UTF-8"));
		}
		
		channel.close();
		con.close();
		
		
	}

}
