package com.zfsoft.api.event;

import java.util.Properties;

import org.springframework.enhanced.context.event.EnhancedEvent;

@SuppressWarnings("serial")
public class PropsUpdateEvent extends EnhancedEvent<Properties> {
	
	public PropsUpdateEvent(Object source, Properties props) {
		super(source, props);
	}
	
}
