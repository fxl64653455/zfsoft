/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.event;

import org.springframework.context.ApplicationEvent;

import com.zfsoft.boot.apimgr.setup.event.bo.ApiAudit;

public class ApiAuditEvent extends ApplicationEvent{

	private static final long serialVersionUID = -825312754882542989L;
	
	private ApiAudit audit;
	
	public ApiAudit getAudit() {
		return audit;
	}

	public ApiAuditEvent(ApiAudit audit) {
		super(audit);
		this.audit = audit;
	}
	
}
