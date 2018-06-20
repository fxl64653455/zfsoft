/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.disruptor.handlers;

import org.springframework.stereotype.Component;

import com.zfsoft.boot.disruptor.annotation.EventRule;
import com.zfsoft.boot.disruptor.event.DisruptorBindEvent;
import com.zfsoft.boot.disruptor.event.handler.DisruptorHandler;
import com.zfsoft.boot.disruptor.event.handler.chain.HandlerChain;

@EventRule("/Event-Output/TagA-Output/**")
@Component("smsHandler")
public class SmsPostDisruptorHandler implements DisruptorHandler<DisruptorBindEvent> {

	@Override
	public void doHandler(DisruptorBindEvent event, HandlerChain<DisruptorBindEvent> handlerChain) throws Exception {
		System.out.println(event.getBind());
	}

}
