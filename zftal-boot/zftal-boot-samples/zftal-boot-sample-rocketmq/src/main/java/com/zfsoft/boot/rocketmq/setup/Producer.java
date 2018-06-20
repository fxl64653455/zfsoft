package com.zfsoft.boot.rocketmq.setup;

import java.util.Date;

import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zfsoft.boot.rocketmq.RocketmqProducerTemplate;

@Component
@EnableScheduling
public class Producer implements CommandLineRunner {

	@Autowired(required = false)
	protected RocketmqProducerTemplate rocketmqTemplate;
	
	@Scheduled(fixedDelay = 3000) // 每3s执行1次
	public void sendToA() throws Exception {
		
		// send topic.
		Message msg2 = new Message("Topic-DC-Input", // topic
				"TagA-Input", // tag
				"KKK", // key用于标识业务的唯一性； key 消息关键词，多个Key用KEY_SEPARATOR隔开（查询消息使用）
				(new Date() + ": hi,RocketMQ(topic) I'm Spring zftal-boot-datay").getBytes()// body 二进制字节数组
		);
		
		rocketmqTemplate.send(msg2);

	}
	
	@Scheduled(fixedDelay = 3000) // 每3s执行1次
	public void sendToB() throws Exception {
		
		// send topic.
		Message msg2 = new Message("Topic-DC-Input", // topic
				"TagB-Input", // tag
				"KKK", // key用于标识业务的唯一性； key 消息关键词，多个Key用KEY_SEPARATOR隔开（查询消息使用）
				(new Date() + ": hi,RocketMQ(topic) I'm Spring zftal-boot-datay").getBytes()// body 二进制字节数组
		);
		
		rocketmqTemplate.send(msg2);

	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Message was sent to the Queue");
	}

}