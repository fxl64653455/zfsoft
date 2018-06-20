package com.zfsoft.boot.rocketmq.disruptor;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.zfsoft.boot.rocketmq.event.RocketmqDisruptorEvent;

public class RocketmqDataOrderlyEventTranslator implements EventTranslatorOneArg<RocketmqDisruptorEvent, MessageExt> {
	
	private ConsumeOrderlyContext context;
	
	public RocketmqDataOrderlyEventTranslator(ConsumeOrderlyContext context) throws Exception {
		this.context = context;
	}
	
	@Override
	public void translateTo(RocketmqDisruptorEvent event, long sequence, MessageExt msgExt) {
		
		event.setMessageExt(msgExt);
		event.setTopic(msgExt.getTopic());
		event.setTag(msgExt.getTags());
		event.setBody(msgExt.getBody());
		
	}

	public ConsumeOrderlyContext getContext() {
		return context;
	}

	public void setContext(ConsumeOrderlyContext context) {
		this.context = context;
	}
	
}