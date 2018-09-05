package com.xiangyang.rocketmq.application;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

/**
 * 以单向模式发送消息 如日志收集
 * @author 15138
 *
 */
public class OnewayProducer {

	public static void main(String[] args) {
//		DefaultMQProducer producer = MqProducerConnectionUtil.getConnection();
//		producer.start();
//		for (int i = 0; i < 100; i++) {
//            //Create a message instance, specifying topic, tag and message body.
//            Message msg = new Message("TopicTest" /* Topic */,
//                "TagA" /* Tag */,
//                ("Hello RocketMQ " +
//                    i).getBytes("") /* Message body */
//            );
//            //Call send message to deliver message to one of brokers.
//            producer.sendOneway(msg);
//
//        }
//        //Shut down once the producer instance is not longer in use.
//        producer.shutdown();
	}

}
