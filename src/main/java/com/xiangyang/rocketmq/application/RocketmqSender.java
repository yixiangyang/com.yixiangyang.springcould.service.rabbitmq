package com.xiangyang.rocketmq.application;

import java.util.List;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

public class RocketmqSender {
	
	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("producer1");
        
        producer.setNamesrvAddr("192.168.140.131:9876");
        
        //调用start()方法启动一个producer实例
        producer.start();

        //发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest",// topic
                        "TagA",// tag
                        ("Hello RocketMQ dddddddddddddddd" + i).getBytes("UTF-8")// body
                );
                
                //调用producer的send()方法发送消息
                //这里调用的是同步的方式，所以会有返回结果
                SendResult sendResult = producer.send(msg);
                
                //打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        Message message = new Message("TopicTest",// topic
                "TagA",// tag
                ("哎哎哎" ).getBytes("UTF-8"));// bod
        SendResult send = producer.send(message,5555);
        System.out.println(send);
        producer.shutdown();

	}

}
