package com.xiangyang.rocketmq.application;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

public class MqProducerConnectionUtil {
	public static DefaultMQProducer getConnection(){
		DefaultMQProducer producer = new DefaultMQProducer("producer1");
        producer.setNamesrvAddr("192.168.140.131:9876");
        return producer;
	}
}
