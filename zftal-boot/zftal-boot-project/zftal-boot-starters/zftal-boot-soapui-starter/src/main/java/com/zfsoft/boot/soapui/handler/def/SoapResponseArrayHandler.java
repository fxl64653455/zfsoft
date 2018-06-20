 package com.zfsoft.boot.soapui.handler.def;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.support.soap.SoapUtils;
import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;
import com.eviware.soapui.model.iface.Response;
import com.eviware.soapui.support.SoapUIException;
import com.zfsoft.boot.soapui.handler.SoapResponseHandler;
import com.zfsoft.boot.soapui.utils.SoapuiResponseUtils;

/**
 * 请求响应处理：返回String对象
 */
public class SoapResponseArrayHandler implements SoapResponseHandler<String[]> {
 
	@Override
	public String[] handleResponse(WsdlOperation operationInst, Response response) throws SoapUIException {
		// 响应内容
		String responseContent = response.getContentAsString();
		try {
			if(SoapUtils.isSoapFault(responseContent)) {
				
			}
		} catch (XmlException e) {
			e.printStackTrace();
		}
		
		SoapVersion soapVersion = operationInst.getInterface().getSoapVersion();
		return SoapuiResponseUtils.parseResponseToArray(responseContent, soapVersion);
	}
	
}

 
