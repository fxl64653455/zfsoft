/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;
import com.zfsoft.io.utils.IOUtils;

/**
 * 
 * @className	： ApiSDKController
 * @description	： Api SDK下载
 * @author 		：万大龙（743）
 * @date		： 2017年10月30日 下午4:26:58
 * @version 	V1.0
 */
@Controller
@RequestMapping("/manager/api/sdk/")
public class ApiSDKController {
	
	@Autowired
	private IApiDeployService apiDeployService;
	@Autowired
	private ApiOutputHandler apiOutputHandler;
	
	/**
	 * 
	 * @description	： Api SDK下载
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月30日 下午4:27:44
	 * @param deployId
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("download")
	@ResponseBody
	public void download(HttpServletResponse response, HttpServletRequest request) throws Exception{
		InputStream input = null;
		try {
			Resource sdk = new ClassPathResource("static/api-sdk.zip");
			input = sdk.getInputStream();
			response.setContentLengthLong(sdk.contentLength());
			response.setContentType("application/force-download"); // 设置强制下载不打开
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=API SDK.zip"); // 设置文件名
			IOUtils.copy(input, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	/**
	 * 
	 * @description	： Api 文档下载
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月30日 下午4:27:44
	 * @param deployId
	 * @param model
	 * @return
	 */
	@RequestMapping("doc/{deployId}")
	@ResponseBody
	public ResponseEntity<byte[]> document(@PathVariable(value = "deployId") String deployId, HttpServletResponse response, HttpServletRequest request){
 		if (!StringUtils.isEmpty(deployId)) {
 			//根据接口发布数据信息，渲染一个相应的文档
 			getApiDeployService().getModel(deployId);
		}
		return null;
	}

	public IApiDeployService getApiDeployService() {
		return apiDeployService;
	}

	public void setApiDeployService(IApiDeployService apiDeployService) {
		this.apiDeployService = apiDeployService;
	}

	public ApiOutputHandler getApiOutputHandler() {
		return apiOutputHandler;
	}

	public void setApiOutputHandler(ApiOutputHandler apiOutputHandler) {
		this.apiOutputHandler = apiOutputHandler;
	}
	
}
