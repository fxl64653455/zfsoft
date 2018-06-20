/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.disruptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.zfsoft.boot.disruptor.event.DisruptorBindEvent;
import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public class DisruptorTemplate {

	@Autowired	
	protected Disruptor<DisruptorEvent> disruptor;
	@Autowired	
	protected EventTranslatorOneArg<DisruptorEvent, DisruptorEvent> oneArgEventTranslator;
	
	public void publishEvent(DisruptorBindEvent event) {
		disruptor.publishEvent(oneArgEventTranslator, event);
	}
	
	public void publishEvent(String event, String tag, Object body) {
		DisruptorBindEvent bindEvent = new DisruptorBindEvent();
		bindEvent.setEvent(event);
		bindEvent.setTag(tag);
		bindEvent.setBody(body);
		disruptor.publishEvent(oneArgEventTranslator, bindEvent);
	}
	
	public void publishEvent(String event, String tag, String key, Object body) {
		DisruptorBindEvent bindEvent = new DisruptorBindEvent();
		bindEvent.setEvent(event);
		bindEvent.setTag(tag);
		bindEvent.setKey(key);
		bindEvent.setBody(body);
		disruptor.publishEvent(oneArgEventTranslator, bindEvent);
	}
	  
}
