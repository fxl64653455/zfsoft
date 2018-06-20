package com.zfsoft.boot.disruptor.event.translator;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.zfsoft.boot.disruptor.event.DisruptorBindEvent;
import com.zfsoft.boot.disruptor.event.DisruptorEvent;
import com.zfsoft.boot.disruptor.util.StringUtils;

public class DisruptorEventOneArgTranslator implements EventTranslatorOneArg<DisruptorEvent, DisruptorEvent> {

	@Override
	public void translateTo(DisruptorEvent event, long sequence, DisruptorEvent bind) {
		
		event.setEvent(bind.getEvent());
		event.setTag(bind.getTag());
		event.setKey(StringUtils.hasText(bind.getKey()) ? bind.getKey() : String.valueOf(sequence));
		if(bind.getSource() != null){
			event.setSource(bind.getSource());
		}
		if(event instanceof DisruptorBindEvent){
			DisruptorBindEvent bindEvent = (DisruptorBindEvent)event;
			bindEvent.bind(bind);
		}
	}
	
}

