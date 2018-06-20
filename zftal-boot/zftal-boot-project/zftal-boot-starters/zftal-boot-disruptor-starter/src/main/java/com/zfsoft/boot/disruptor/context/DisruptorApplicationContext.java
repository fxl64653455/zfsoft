package com.zfsoft.boot.disruptor.context;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zfsoft.boot.disruptor.context.event.DisruptorEventPublisher;
import com.zfsoft.boot.disruptor.event.DisruptorApplicationEvent;
import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public class DisruptorApplicationContext implements ApplicationContextAware, DisruptorEventPublisher {

	protected ApplicationContext applicationContext;
	
	@Override
	public void publishEvent(DisruptorEvent event) {
		applicationContext.publishEvent(new DisruptorApplicationEvent(event));
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
	

