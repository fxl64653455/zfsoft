package com.zfsoft.boot.soapui.handler;

import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.support.SoapUIException;

public interface SoapRequestHandler<T> {

	String handleRequest(WsdlOperation operationInst, WsdlRequest request,T params) throws SoapUIException;
	
}
