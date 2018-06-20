package com.zfsoft.api.event.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.enhanced.context.event.handler.EventHandler;
import org.springframework.stereotype.Component;

import com.zfsoft.api.event.PropsUpdateEvent;

@Component
public class PropsUpdateEventHandler implements EventHandler<PropsUpdateEvent> {

	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public void handle(PropsUpdateEvent event) {

		if (LOG.isDebugEnabled()) {
			
			LOG.debug("Business Throwing [ Method ： {} , ArgNames : {} , Cause : {} ] ", event.getBind().toString());

		}

	}

}
