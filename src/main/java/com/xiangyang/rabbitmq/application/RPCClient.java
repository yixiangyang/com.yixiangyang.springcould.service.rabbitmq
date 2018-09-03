package com.xiangyang.rabbitmq.application;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import io.netty.handler.timeout.TimeoutException;

public class RPCClient {
	private Connection con;
	private Channel channel;
	private String requestQueueName = "rpc_queue";

	public RPCClient() throws Exception {
		con = ConnectionUtil.getConnection();
		channel = con.createChannel();
	}

	public String call(String message) throws IOException, InterruptedException {
		final String corrId = UUID.randomUUID().toString();
		String replyQueueName = channel.queueDeclare().getQueue();
		AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName)
				.build();

		channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

		final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

		String ctag = channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				if (properties.getCorrelationId().equals(corrId)) {
					response.offer(new String(body, "UTF-8"));
				}
			}
		});

		String result = response.take();
		channel.basicCancel(ctag);
		return result;
	}

	public void close() throws IOException {
		con.close();
	}

	public static void main(String[] args) throws Exception {
		RPCClient fibonacciRpc = null;
		String response = null;
		try {
			fibonacciRpc = new RPCClient();

			for (int i = 0; i < 32; i++) {
				String i_str = Integer.toString(i);
				System.out.println(" [x] Requesting fib(" + i_str + ")");
				response = fibonacciRpc.call(i_str);
				System.out.println("  Got '" + response + "'");
			}
		} catch (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (fibonacciRpc != null) {
				try {
					fibonacciRpc.close();
				} catch (IOException _ignore) {
				}
			}
		}

	}

}
