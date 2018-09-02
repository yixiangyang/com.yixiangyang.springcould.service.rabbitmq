package com.xiangyang.rabbitmq.application;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 直接发送消息 不是采用扇出 的形式发送消息
 * @author 伊向阳
 *
 */
public class SengLogDirect {
	private static final String EXCHANGE_NAME = "direct_logs";
	
	public static void main(String[] args) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		Channel channel = con.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

	    String severity = "info";
	    String message = "这个是日志   信息 。。。";

	    channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
	    System.out.println(" Sent '" + severity + "':'" + message + "'");

	    channel.close();
	    con.close();
	}

}
