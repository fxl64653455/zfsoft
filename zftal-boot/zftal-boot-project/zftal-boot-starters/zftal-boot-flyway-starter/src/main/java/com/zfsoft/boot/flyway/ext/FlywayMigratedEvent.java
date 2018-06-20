/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.flyway.ext;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class FlywayMigratedEvent extends ApplicationEvent {

	public FlywayMigratedEvent(Object source) {
		super(source);
	}
	
	

}
