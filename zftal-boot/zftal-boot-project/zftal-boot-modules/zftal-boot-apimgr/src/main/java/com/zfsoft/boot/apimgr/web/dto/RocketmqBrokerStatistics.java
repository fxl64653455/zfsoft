/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import java.util.List;
import java.util.Map;

public class RocketmqBrokerStatistics {

	/** 响应状态 */
	private String status;
	/** 异常信息 */
	private String errMsg;
	/** 统计数据 */
	private Map<String,List<String>> data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Map<String,List<String>> getData() {
		return data;
	}

	public void setData(Map<String,List<String>> data) {
		this.data = data;
	}
	
}
