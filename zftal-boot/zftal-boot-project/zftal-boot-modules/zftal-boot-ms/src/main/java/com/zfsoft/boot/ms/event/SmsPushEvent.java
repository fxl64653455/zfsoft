package com.zfsoft.boot.ms.event;

import org.springframework.enhanced.context.event.EnhancedEvent;

import com.zfsoft.boot.ms.MessageBody;

@SuppressWarnings("serial")
public class SmsPushEvent extends EnhancedEvent<MessageBody> {

	public SmsPushEvent(Object source, MessageBody bind) {
		super(source, bind);
	}
	
}
