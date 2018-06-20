package com.zfsoft.boot.ms.event;

import org.springframework.enhanced.context.event.EnhancedEvent;

import com.zfsoft.boot.ms.MessageBody;


@SuppressWarnings("serial")
public class EmailPushEvent extends EnhancedEvent<MessageBody> {

	public EmailPushEvent(Object source, MessageBody bind) {
		super(source, bind);
	}
	
}
