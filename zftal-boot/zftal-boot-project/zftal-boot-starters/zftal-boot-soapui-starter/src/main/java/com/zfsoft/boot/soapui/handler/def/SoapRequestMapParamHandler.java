package com.zfsoft.boot.soapui.handler.def;

import java.util.Map;

import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;
import com.eviware.soapui.support.SoapUIException;
import com.zfsoft.boot.soapui.handler.SoapRequestHandler;
import com.zfsoft.boot.soapui.utils.SoapuiRequestUtils;

public class SoapRequestMapParamHandler implements SoapRequestHandler<Map<String, Object>> {

	@Override
	public String handleRequest(WsdlOperation operationInst, WsdlRequest request, Map<String, Object> params) throws SoapUIException {
		// generate the request content from the schema
		String requestXML = operationInst.createRequest( true );
		// 对 requestContent 进行动态补全
		SoapVersion soapVersion = operationInst.getInterface().getSoapVersion();
		return SoapuiRequestUtils.buildSoapMessage(requestXML, soapVersion, params);
	}

}
