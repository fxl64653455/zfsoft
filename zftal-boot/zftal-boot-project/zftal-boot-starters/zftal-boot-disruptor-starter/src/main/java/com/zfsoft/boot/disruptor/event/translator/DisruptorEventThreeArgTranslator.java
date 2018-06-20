/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.disruptor.event.translator;

import com.lmax.disruptor.EventTranslatorThreeArg;
import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public class DisruptorEventThreeArgTranslator implements EventTranslatorThreeArg<DisruptorEvent, String, String, String> {

	@Override
	public void translateTo(DisruptorEvent dtEevent, long sequence, String event, String tag, String key) {
		dtEevent.setEvent(event);
		dtEevent.setTag(tag);
		dtEevent.setKey(key);
	}
	
}
