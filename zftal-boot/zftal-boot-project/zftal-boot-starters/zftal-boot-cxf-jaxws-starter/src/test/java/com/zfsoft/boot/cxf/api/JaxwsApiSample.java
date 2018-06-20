/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.cxf.api;


import java.lang.reflect.InvocationHandler;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.zfsoft.boot.cxf.jaxws.JaxwsApi;

@WebService(serviceName = "sample", // 与接口中指定的name一致
		targetNamespace = "http://ws.cxf.com/"// , // 与接口中的命名空间一致,一般是接口的包名倒
// endpointInterface = "org.apache.cxf.spring.boot.api.JaxwsSample"// 接口地址
)
public class JaxwsApiSample extends JaxwsApi {

	public JaxwsApiSample() {
	}

	public JaxwsApiSample(InvocationHandler handler) {
		super(handler);
	}

	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public String sayHello(@WebParam(name = "userName") String name) {
		
		//getHandler().invoke(this, method, args);
		//Method method = this.getClass().getDeclaredMethod(name, $sig);
		//getHandler().invoke($0, method, $args);
		
		return "Hello ," + name;
	}

	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public String sayHello2(@WebParam(name = "userName") String name) {
		return "Hello ," + name;
	}
	
	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public String invoke(@WebParam(name = "userName") String name) {
		
		//this.getHandler().invoke(this, method, args)
		
		return "Hello ," + name;
	}
	
}