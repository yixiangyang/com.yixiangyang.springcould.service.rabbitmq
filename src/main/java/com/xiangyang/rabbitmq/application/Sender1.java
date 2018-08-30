package com.xiangyang.rabbitmq.application;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender1 {
	private final static String QUEUE_NAME = "hello1";
	
	public static void main(String[] args) throws Exception {
		// 获取连接
		Connection con = ConnectionUtil.getConnection();
		// 创建通道
		Channel channel = con.createChannel();
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String[] s = {"2","3","这个真是个傻逼"};
		String message = getMessage(s);
		for(int i=0;i<100;i++){
			channel.basicPublish("", QUEUE_NAME, null, (message +i).getBytes());
		}
		channel.close();
		con.close();

	}
	
	private static String getMessage(String[] strings){
	    if (strings.length < 1)
	        return "Hello World!";
	    return joinStrings(strings, " ");
	}
	
	private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	        words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	}

}
