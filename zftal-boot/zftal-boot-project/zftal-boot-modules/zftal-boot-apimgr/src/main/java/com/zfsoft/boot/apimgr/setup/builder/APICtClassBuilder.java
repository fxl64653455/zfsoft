package com.zfsoft.boot.apimgr.setup.builder;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.Builder;

import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;
import com.zfsoft.boot.apimgr.web.dto.ApiParam;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;

import javassist.CtClass;

/**
 * 
 * @className	： APICtClassBuilder
 * @description	： API接口Class构建器统一入口
 * @author 		：万大龙（743）
 * @date		： 2017年10月12日 下午9:04:45
 * @version 	V1.0
 */
public class APICtClassBuilder implements Builder<CtClass> {
	
	// 构建动态类
	private CtClass ctclass  = null;
	
	private ApiDeployModel apiDeploy = null;
	
	private ApiOutputHandler apiOutputHandler = null;
	
	private static final String RESTFUL_API_PREFIX = "com.zfsoft.boot.api.http.RestfulApi";
	
	public APICtClassBuilder(ApiOutputHandler apiOutputHandler,final ApiDeployModel apiDeploy) {
		this.apiOutputHandler = apiOutputHandler;
		this.apiDeploy = apiDeploy;
	}
	
	public APICtClassBuilder wsApi() throws Exception {
		
		ApiDeployDto dto = apiOutputHandler.output(apiDeploy);
		
		// 使用发布地址的md5Hex值作为bean名称
		String beanName = DigestUtils.md5Hex(apiDeploy.getAddr().getBytes());
		
		List<ApiParam> params = dto.getParamList();
		String[] arr = new String[params.size()];
		for(int i = 0;i < params.size();i ++) {
			arr[i] = params.get(i).getKey();
		}
		
		// 动态构建WebService
		ctclass  = new JaxwsApiCtClassBuilder(beanName)
				.suffix(dto.getNamespace())
				.pKeys(arr)
				.operationName("Operation")
				.build(apiDeploy.getId());
		
		return this;
	}
	
	public APICtClassBuilder restApi() throws Exception {
		
		// 使用发布地址的Base64值作为bean名称
		String beanName = Base64.encodeBase64String(apiDeploy.getAddr().getBytes());
		
		ctclass  = new ApiControllerCtClassBuilder(RESTFUL_API_PREFIX + beanName)
				.method(apiDeploy.getMethod())
				.addr(apiDeploy.getAddr())
				.contentType("application/json; charset=UTF-8")
				.build(apiDeploy.getId());
		
		return this;
	}
	
	@Override
	public CtClass build() {
        return ctclass;
	}

}
