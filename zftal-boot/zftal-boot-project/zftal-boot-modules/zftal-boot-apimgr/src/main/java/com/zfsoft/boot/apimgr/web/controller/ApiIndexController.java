/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.util.enums.DeployTypeEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;
import com.zfsoft.boot.apimgr.web.handler.WsCxfFaults;
import com.zfsoft.boot.webv5.web.handler.HttpExceptions;
import com.zfsoft.io.utils.IOUtils;
import com.zfsoft.spring.utils.UserAgentUtils;
import com.zfsoft.web.utils.WebContextUtils;

import eu.bitwalker.useragentutils.Browser;

/**
 * 
 * @className	： ApiIndexController
 * @description	： 查询接口页面相关
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月30日 下午4:23:26
 * @version 	V1.0
 */
@Controller
@RequestMapping("/manager/api/index/")
public class ApiIndexController {
	
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private IApiDeployService apiDeployService;
	@Autowired
	private ApiOutputHandler apiOutputHandler;
	
	/**
	 * 
	 * @description	： 页面首页
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午7:26:24
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String list(ModelMap model) {
		return "html/api/index/list";
	}
	
	/**
	 * 
	 * @description	： 接口详情
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午7:26:43
	 * @param apiId
	 * @param model
	 * @return
	 */
	@GetMapping("detail/{deployId}")
	public String desc(@PathVariable(value = "deployId")String deployId,ModelMap model,HttpServletRequest request) {
		ApiDeployModel apiDeploy = getApiDeployService().findDeployById(deployId, true);
		
		ApiDeployDto apiDto = getApiOutputHandler().output(apiDeploy);
		if (DeployTypeEnum.valueOfIgnoreCase(apiDto.getType()).compareTo(DeployTypeEnum.REST) == 0) {
			model.addAttribute("errorList", HttpExceptions.errors());
		} else if (DeployTypeEnum.valueOfIgnoreCase(apiDto.getType()).compareTo(DeployTypeEnum.CXF) == 0) {
			model.addAttribute("errorList", WsCxfFaults.errors());
		}
		model.addAttribute("baseUrl", WebContextUtils.getBaseContextPath(request));
		model.addAttribute("deployDto", apiDto);
		model.addAttribute("oauthEnable", oauth.isEnabled());
		model.addAttribute("target", "html/api/index/detail");
		return "html/api/index/detail";
	}
	
	/**
	 * 
	 * @description	： 查询接口信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午7:26:53
	 * @param queryModel
	 * @param name
	 * @return
	 */
	@PostMapping("search")
	public @ResponseBody Object search(QueryModel queryModel, @RequestParam(value = "apiName")String apiName) {
		ApiDeployModel deployModel = new ApiDeployModel();
		deployModel.setApiName(apiName);
		deployModel.setQueryModel(queryModel);
		return getApiDeployService().getPagedSearchList(deployModel);
	}
	
	/**
	 * 根据deployId查询图标
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月24日 下午5:50:50
	 * @param deployId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/icon/{deployId}")
	public @ResponseBody ResponseEntity<byte[]> showZp(@PathVariable(value = "deployId") String deployId, HttpServletResponse response, HttpServletRequest request){
 		if (!StringUtils.isEmpty(deployId)) {
			byte[] icon = apiDeployService.getIcon(deployId);
			if(icon == null) {
				Resource blankImage = new ClassPathResource("static/assets/images/renkou.png");
				try {
					InputStream in = blankImage.getInputStream();
					icon = new byte[(int)blankImage.contentLength()];
					in.read(icon);
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(UserAgentUtils.getBrowser(request).compareTo(Browser.FIREFOX) == 0){
				OutputStream out = null;
				try {
					out = response.getOutputStream();
					out.write(icon);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					IOUtils.closeOutput(out);
				}
			}else{
				try {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.IMAGE_JPEG);
					return new ResponseEntity<byte[]>(icon, headers, HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
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
