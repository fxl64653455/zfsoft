/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.disruptor.event.translator;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public class DisruptorEventTwoArgTranslator implements EventTranslatorTwoArg<DisruptorEvent, String, String> {

	@Override
	public void translateTo(DisruptorEvent dtEevent, long sequence, String event, String tag) {
		dtEevent.setEvent(event);
		dtEevent.setTag(tag);
		dtEevent.setKey(String.valueOf(sequence));
	}
	
}