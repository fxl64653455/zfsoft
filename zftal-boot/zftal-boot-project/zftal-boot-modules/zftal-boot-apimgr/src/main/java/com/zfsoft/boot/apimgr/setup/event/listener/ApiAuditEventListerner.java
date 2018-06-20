/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zfsoft.boot.apimgr.setup.event.ApiAuditEvent;

@Component
public class ApiAuditEventListerner implements ApplicationListener<ApiAuditEvent> {

	@Async
	@Override
	public void onApplicationEvent(ApiAuditEvent event) {
		System.out.println("=======:" + JSON.toJSONString(event.getAudit()));
	}
	
}
