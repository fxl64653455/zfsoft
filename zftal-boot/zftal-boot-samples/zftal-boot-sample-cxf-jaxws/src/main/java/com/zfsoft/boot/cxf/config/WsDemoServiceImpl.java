package com.zfsoft.boot.cxf.config;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 接口实现
 */
@WebService(serviceName = "get", // 与接口中指定的name一致
		targetNamespace = "http://ws.zfsoft.com/"//, // 与接口中的命名空间一致,一般是接口的包名倒
		// endpointInterface = "com.zfsoft.boot.datay.setup.ws.CommonService"// 接口地址
)
public class WsDemoServiceImpl {

	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public String sayHello(@WebParam(name = "userName") String name) {

		return "Hello ," + name;
	}

}