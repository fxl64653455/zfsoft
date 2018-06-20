package com.zfsoft.boot.soapui;

import com.zfsoft.boot.soapui.wsdl.WsdlInfo;
import com.zfsoft.boot.soapui.wsdl.WsdlInterfaceInfo;
import com.zfsoft.boot.soapui.wsdl.WsdlOperationInfo;

/**
 * http://www.cnblogs.com/coshaho/p/5105545.html
 * http://www.cnblogs.com/coshaho/p/5689738.html
 */
public class SoapuiWsdl_Test {
	
	public static void main(String[] args) throws Exception {
        
		String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
		WsdlInfo wsdlInfo = new WsdlInfo(wsdlUrl);
		System.out.println("WSDL URL is " + wsdlInfo.getWsdlUrl());

		for (WsdlInterfaceInfo interfaceInfo : wsdlInfo.getInterfaces()) {
			System.out.println("Interface name is " + interfaceInfo.getInterfaceName());
			for (String ads : interfaceInfo.getAdrress()) {
				System.out.println("Interface address is " + ads);
			}
			for (WsdlOperationInfo operation : interfaceInfo.getOperations()) {
				
				
				System.out.println("operation name is " + operation.getOperationName());
				System.out.println("operation request is ");
				System.out.println("operation request is " + operation.getRequestXml());
				System.out.println("operation response is ");
				System.out.println(operation.getResponseXml());
			}
		}
	}
}