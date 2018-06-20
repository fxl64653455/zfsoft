package com.zfsoft.boot.rocketmq.disruptor;

import com.zfsoft.boot.rocketmq.event.RocketmqDisruptorEvent;

import com.lmax.disruptor.EventFactory;

public class RocketmqDataEventFactory implements EventFactory<RocketmqDisruptorEvent> {

	@Override
	public RocketmqDisruptorEvent newInstance() {
		return new RocketmqDisruptorEvent(this);
	}
	
}
