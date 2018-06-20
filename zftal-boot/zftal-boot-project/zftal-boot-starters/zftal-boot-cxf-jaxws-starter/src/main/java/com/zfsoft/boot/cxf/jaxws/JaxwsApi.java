/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.cxf.jaxws;


import java.lang.reflect.InvocationHandler;

public abstract class JaxwsApi {

	private InvocationHandler handler;
	
	public JaxwsApi() {
	}
	
	public JaxwsApi(InvocationHandler handler) {
		this.handler = handler;
	}

	public InvocationHandler getHandler() {
		return handler;
	}
	
	
}
