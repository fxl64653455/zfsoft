/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util;

import java.util.logging.Logger;

import org.apache.cxf.common.i18n.Message;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.interceptor.Fault;

public class CxfFaultUtils {

	 private static final Logger LOG = LogUtils.getL7dLogger(CxfFaultUtils.class);
	 
	public static Fault handleFault(Fault fault){
		if(fault.getMessage().contains("java.net.ConnectException")) {
			return new Fault(new Message("第三方接口服务目前无法使用（超载或停机维护）.", LOG), fault);
		}
		//Messages.getMessage("noEndpoint")
		return new Fault(new Message("第三方接口调用异常.", LOG), fault);
	}
	
}
