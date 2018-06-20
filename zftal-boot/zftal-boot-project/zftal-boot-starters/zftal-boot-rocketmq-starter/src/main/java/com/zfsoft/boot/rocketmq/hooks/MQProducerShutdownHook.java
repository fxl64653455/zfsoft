package com.zfsoft.boot.rocketmq.hooks;

import org.apache.rocketmq.client.producer.MQProducer;

public class MQProducerShutdownHook extends Thread{
	
	private MQProducer producer;
	
	public MQProducerShutdownHook(MQProducer producer) {
		this.producer = producer;
	}
	
	@Override
	public void run() {
		producer.shutdown();
	}
	
}
