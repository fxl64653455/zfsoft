/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInfoService;
import com.zfsoft.boot.apimgr.web.handler.ApiHandlerExec;

@Controller
@RequestMapping("/manager/api/info/debug")
public class ApiInfoDebugController{
	
	@Autowired
	private IApiInfoService apiInfoService;
	@Autowired
	private ApiHandlerExec handler;
	
	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public String index(@RequestParam String apiId,ModelMap model) {
		
		ApiInfoModel apiInfo = apiInfoService.getModel(apiId);
		model.addAttribute("apiInfo", apiInfo);
		model.addAttribute("desc", JSONObject.parse(apiInfo.getDesc()));
		
		return "html/api/info/debug";
	}
	
	@PostMapping(value = "/exec", produces = {"application/json;charset=UTF-8","text/plain;charset=UTF-8"})
	public @ResponseBody Object exec(@RequestParam Map<String, Object> par) {
		return handler.debugExec(par.get("apiId").toString(), par);
	}
	
}
