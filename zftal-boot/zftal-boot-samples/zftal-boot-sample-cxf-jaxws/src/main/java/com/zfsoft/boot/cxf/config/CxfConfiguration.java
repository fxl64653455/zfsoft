/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.cxf.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfiguration {

	@Autowired
    private Bus bus;
	
	/** 
	 * JAX-WS 
	 * 
	 * // 销毁指定的Ws
	 *	ServerImpl server = endpoint.getServer(addr);
	 *	server.destroy();
	 * 
	 */
	@Bean
	public Endpoint endpoint() {
		 
		// 动态创建、发布 Ws
			
		WsDemoServiceImpl wsapi = new WsDemoServiceImpl();
		
		EndpointImpl endpoint = new EndpointImpl(bus, wsapi);
		
		//接口发布在 addr 目录下
		endpoint.publish("/demo");
		
		return endpoint;
	 
	}
}
