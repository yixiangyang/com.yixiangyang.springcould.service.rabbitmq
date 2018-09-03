package com.xiangyang.rabbitmq.application;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 【Topic】类型的【消息交换机】背后的逻辑类似于【Direct】类型的【消息交换机】 - 使用特定【路由键】发送的消息将被传递到与匹配的【绑定键】绑定的所有队列
 * @author 15138
 *
 */
public class SendLogTopic {
	private static final String EXCHANGE_NAME="topic_logs"; 
	public static void main(String[] args) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		Channel channel = con.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		//主题交流 routingKey 必须是由点分隔的单词列表，上限是255个字节
		String routingKey = "stock.usd.nyse";
		String message = "这个是发送的消息33333";

		channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
		System.out.println("Sent '" + routingKey + "':'" + message + "'");
		
		channel.close();
		con.close();
	}

}
