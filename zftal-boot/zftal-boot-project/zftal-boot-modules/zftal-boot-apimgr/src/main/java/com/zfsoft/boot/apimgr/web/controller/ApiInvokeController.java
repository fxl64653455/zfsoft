/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInvokeModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInfoService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInvokeService;
import com.zfsoft.boot.apimgr.web.dto.ApiInvokeDto;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/info/config")
public class ApiInvokeController extends BaseController{

	@Autowired
	private IApiInfoService apiInfoService;
	@Autowired
	private IApiDeployService apiDeployService;
	@Autowired
	private IApiInvokeService apiInvokeService;
	
	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public String index(@RequestParam String apiId,ModelMap model) {
		
		ApiInfoModel apiInfo = apiInfoService.getModel(apiId);
		model.addAttribute("apiInfo", apiInfo);
		model.addAttribute("desc", JSONObject.parse(apiInfo.getDesc()));
		model.addAttribute("apiList", apiDeployService.getComboList(new ApiDeployModel()));
		
		ApiInvokeModel obj = apiInvokeService.getModel(apiId);
		if(obj != null) {
			model.addAttribute("invokeDeployId", obj.getInvokeDeployId());
			model.addAttribute("paramRelation", JSONArray.parseArray(obj.getParamRelation()));
		}
		
		return "html/api/info/config";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Object save(@Valid ApiInvokeDto obj,BindingResult result) {
		if(result.hasErrors()) {
			String str = "";
			List<ObjectError> errorList = result.getAllErrors();
            for(ObjectError error : errorList){
            	str += error.getDefaultMessage() + ";";
            }
            return ResultUtils.statusMap(STATUS_FAIL, str);
		}
		apiInvokeService.save(obj);
		return successStatus("I99001");
	}
}
