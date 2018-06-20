/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util;

import org.apache.axis.AxisFault;

public class AxisFaultUtils {

	public static AxisFault handleFault(AxisFault fault){
		if(fault.getFaultReason().contains("java.net.ConnectException")) {
			return new AxisFault("第三方接口服务目前无法使用（超载或停机维护）.", fault);
		}
		//Messages.getMessage("noEndpoint")
		return new AxisFault("第三方接口调用异常.", fault);
	}
	
}
