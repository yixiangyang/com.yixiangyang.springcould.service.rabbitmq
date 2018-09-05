package com.xiangyang.rocketmq.application;

import java.io.UnsupportedEncodingException;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * 异步发送消息  主要用于响应时间敏感的业务场景
 * @author 15138
 *
 */
public class AsyncProducer {
	public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        //发送失败的时候 重试的时间默认就是两秒
        producer.setRetryTimesWhenSendFailed(2);
        for(int i=0;i<100;i++) {
        	int index = i;
        	Message message = new Message("topicTest", "TagA", "key", "Hello ".getBytes("UTF-8"));
        	producer.send(message, new SendCallback() {
				
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.println("消息成功了"+index +"    "+sendResult.getMsgId());
					
				}
				
				@Override
				public void onException(Throwable e) {
					System.out.println("消息处理失败"+index +"   " +e);
					
				}
			});
        }
        producer.shutdown();
	}
}
