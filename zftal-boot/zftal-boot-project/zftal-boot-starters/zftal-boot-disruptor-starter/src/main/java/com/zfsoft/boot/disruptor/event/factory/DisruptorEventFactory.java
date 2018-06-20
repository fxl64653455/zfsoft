/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.disruptor.event.factory;

import com.lmax.disruptor.EventFactory;
import com.zfsoft.boot.disruptor.event.DisruptorBindEvent;
import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public class DisruptorEventFactory implements EventFactory<DisruptorEvent> {

	@Override
	public DisruptorEvent newInstance() {
		return new DisruptorBindEvent(this);
	}

}
