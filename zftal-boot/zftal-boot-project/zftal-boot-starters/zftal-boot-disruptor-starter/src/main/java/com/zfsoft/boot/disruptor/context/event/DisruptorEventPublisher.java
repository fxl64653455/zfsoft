package com.zfsoft.boot.disruptor.context.event;

import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public interface DisruptorEventPublisher {

	void publishEvent(DisruptorEvent event);
	
}
