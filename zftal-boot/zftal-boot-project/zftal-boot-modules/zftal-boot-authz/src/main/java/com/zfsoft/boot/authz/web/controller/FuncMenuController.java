package com.zfsoft.boot.authz.web.controller;

import java.util.Collections;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.web.session.User;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.authz.service.svcinterface.IFuncMenuService;
import com.zfsoft.web.context.WebContext;
import com.zfsoft.webmvc.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "权限管理 : 菜单管理")
@Controller
@RequestMapping(value = "/func/nav/")
public class FuncMenuController extends BaseController{

	@Resource(name  = "funcMenuService")
	protected IFuncMenuService funcMenuService;
	
	@ApiOperation(value = "一级菜单列表", notes = "查询一级菜单列表", httpMethod = "POST")
	@PostMapping("topMenuList")
	@ResponseBody
	public Object getTopList(){
		try {
			// 获取登录用户
			User user = getUser();
			//Locale
	        Locale locale = WebContext.getLocale();
			String localeKey = locale.getLanguage() + "_" + locale.getCountry();
			
			return getFuncMenuService().getTopNavMenuList(user.getYhm(), user.getJsdm(), localeKey);
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}

	@ApiOperation(value = "子菜单列表", notes = "根据父级功能菜单代码查询子级菜单列表（深度1）", httpMethod = "POST")
	@ApiImplicitParam(name = "parent", value = "父级功能菜单代码")
	@PostMapping("childMenuList")
	@ResponseBody
	public Object getChildList(@RequestParam("parent") String parentGnmkdm){
		try {
			if(StringUtils.isEmpty(parentGnmkdm)){
				return Collections.EMPTY_MAP;
			}
			
			// 获取登录用户
			User user = getUser();
			//Locale
	        Locale locale = WebContext.getLocale();
			String localeKey = locale.getLanguage() + "_" + locale.getCountry();
			
			return getFuncMenuService().getChildNavMenuList(user.getYhm(), user.getJsdm(), parentGnmkdm, localeKey);
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	
	@ApiOperation(value = "子菜单树结构", notes = "根据父级功能菜单代码查询所有子级菜单组成树型结构数据", httpMethod = "POST")
	@ApiImplicitParam(name = "parent", value = "父级功能菜单代码")
	@PostMapping("childMenuTreeList")
	@ResponseBody
	public Object getChildNavMenuTreeList(@RequestParam("parent") String parentGnmkdm){
		try {
			
			if(StringUtils.isEmpty(parentGnmkdm)){
				return Collections.EMPTY_MAP;
			}
			
			// 获取登录用户
			User user = getUser();
			//Locale
	        Locale locale = WebContext.getLocale();
			String localeKey = locale.getLanguage() + "_" + locale.getCountry();
			
			return getFuncMenuService().getChildNavMenuTreeList(user.getYhm(), user.getJsdm(), parentGnmkdm, localeKey);
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	@ApiOperation(value = "菜单树结构", notes = "根据当前登录用户、角色查询相应所有菜单组成树型结构数据", httpMethod = "POST")
	@PostMapping("treeMenuList")
	@ResponseBody
	public Object getTreeList(){
		try {
			
			// 获取登录用户
			User user = getUser();
			//Locale
	        Locale locale = WebContext.getLocale();
			String localeKey = locale.getLanguage() + "_" + locale.getCountry();
			
			return getFuncMenuService().getNavMenuTreeList(user.getYhm(), user.getJsdm(), localeKey);
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	public IFuncMenuService getFuncMenuService() {
		return funcMenuService;
	}

	public void setFuncMenuService(IFuncMenuService funcMenuService) {
		this.funcMenuService = funcMenuService;
	}
	
}
