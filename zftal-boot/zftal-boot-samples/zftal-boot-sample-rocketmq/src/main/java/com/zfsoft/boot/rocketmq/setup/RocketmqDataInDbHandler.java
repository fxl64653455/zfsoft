package com.zfsoft.boot.rocketmq.setup;

import org.springframework.stereotype.Component;

import com.zfsoft.boot.rocketmq.annotation.RocketmqPushConsumer;
import com.zfsoft.boot.rocketmq.event.RocketmqEvent;
import com.zfsoft.boot.rocketmq.handler.EventHandler;
import com.zfsoft.boot.rocketmq.handler.chain.HandlerChain;

@RocketmqPushConsumer(topic = "Topic-DC-Input", tags = "TagA-Input")
@Component("inDbHandler")
public class RocketmqDataInDbHandler implements EventHandler<RocketmqEvent> {

	@Override
	public void doHandler(RocketmqEvent event, HandlerChain<RocketmqEvent> handlerChain) throws Exception {
		
		System.out.println("==============================================================");
		System.out.println("Rule : /Topic-DC-Input/TagA-Input/** ");
		
		long threadId = Thread.currentThread().getId();
		System.out.println(String.format("Thread Id %s Topic %s Tag %s into db ....", threadId , event.getTopic() , event.getTag() ));
		System.out.println(String.format("Receive New Message:  %s ", event.getMsgBody() ));
		
	}
	

}
