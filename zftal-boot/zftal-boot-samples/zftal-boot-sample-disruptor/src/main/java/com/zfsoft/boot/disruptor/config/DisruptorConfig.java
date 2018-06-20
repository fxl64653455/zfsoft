/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.disruptor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zfsoft.boot.disruptor.DisruptorTemplate;
import com.zfsoft.boot.disruptor.event.DisruptorBindEvent;


@Configuration
@EnableScheduling
public class DisruptorConfig {

	@Autowired	
	protected DisruptorTemplate disruptorTemplate;

	@Scheduled(fixedDelay = 1000) // 每1s执行1次
	public void send() throws Exception {
		
		DisruptorBindEvent event = new DisruptorBindEvent(this, "message " + Math.random());
		
		event.setEvent("Event-Output");
		event.setTag("TagA-Output");
		event.setKey("id-" + Math.random());
		
		disruptorTemplate.publishEvent(event);
		
	}
	
	@Scheduled(fixedDelay = 1000) // 每1s执行1次
	public void send2() throws Exception {
		
		DisruptorBindEvent event = new DisruptorBindEvent(this, "message " + Math.random());
		
		event.setEvent("Event-Output");
		event.setTag("TagB-Output");
		event.setKey("id-" + Math.random());
		
		disruptorTemplate.publishEvent(event);
		
	}
	
}
