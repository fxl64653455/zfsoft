/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import java.util.List;
import java.util.Map;

import com.zfsoft.boot.apimgr.util.enums.DataExchangeTypeEnum;

public class InputColumn {

	private List<Map<String, String>> input;
	private DataExchangeTypeEnum exchType;
	
	public List<Map<String, String>> getInput() {
		return input;
	}
	public void setInput(List<Map<String, String>> input) {
		this.input = input;
	}
	public DataExchangeTypeEnum getExchType() {
		return exchType;
	}
	public void setExchType(DataExchangeTypeEnum exchType) {
		this.exchType = exchType;
	}
	

}