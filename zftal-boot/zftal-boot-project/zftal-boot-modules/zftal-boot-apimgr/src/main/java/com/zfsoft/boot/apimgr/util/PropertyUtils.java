/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util;

import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.apimgr.web.dto.ApiMetircDto;

public class PropertyUtils {

	public static void copyProperties(ApiMetircModel model, ApiMetircDto dto) {
		
		model.setId(dto.getId());
		model.setAddr(dto.getAddr());
		model.setAppKey(dto.getAppKey());
		model.setBizId(dto.getBizId());
		model.setBizName(dto.getBizName());
		model.setBrowserKernel(dto.getBrowserKernel());
		model.setBrowserName(dto.getBrowserName());
		model.setBrowserType(dto.getBrowserType());
		model.setBrowserVer(dto.getBrowserVer());
		model.setDeviceName(dto.getDeviceName());
		model.setOsMfr(dto.getOsMfr());
		model.setOsName(dto.getOsName());
		model.setOsVer(dto.getOsVer());
		model.setUri(dto.getUri());
		
	}

}
