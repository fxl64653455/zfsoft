/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiAppModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiAppService;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.web.dto.ApiAppDto;
import com.zfsoft.webmvc.controller.BaseController;

/**
 * 
 * @className	： ApiAppController
 * @description	： 我的应用相关
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月29日 下午4:46:31
 * @version 	V1.0
 */
@Controller
@RequestMapping("/manager/api/app")
public class ApiAppController extends BaseController{
	
	@Autowired
	private IApiAppService appService;
	
	/**
	 * 
	 * @description	： 我的应用首页
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:19:20
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/index", produces = "text/html; charset=UTF-8")
	public String index(ModelMap model) {
		return "html/api/app/index";
	}
	
	/**
	 * 
	 * @description	： 表格数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:20:08
	 * @param app
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N060501, ywmc = "应用列表查询", czms = "我的应用分页数据查询", czlx = BusinessType.SELECT)
	@PostMapping("/getTableData")
	public @ResponseBody Object getTableData(ApiAppModel app) {
		try {
			app.setAppOwner(getUser().getYhm());
			QueryModel queryModel = app.getQueryModel();
			List<ApiAppModel> items = appService.getPagedList(app);
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (ApiAppModel apiApp : items) {
				mapList.add(apiApp.toMap());
			}
			queryModel.setItems(mapList);
			return queryModel;
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}
	

	/**
	 * 
	 * @description	： 编辑表单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:19:29
	 * @return
	 */
	@PostMapping(value = "/form", produces = "text/html; charset=UTF-8")
	public String tableForm(ApiAppDto dto,ModelMap model) {
		model.addAttribute("appDto", dto);
		return "html/api/app/form";
	}
	
	/**
	 * 
	 * @description	： 保存数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:20:29
	 * @param app
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N060501, ywmc = "保存应用", czms = "根据应用ID决定新增或修改一个应用", czlx = BusinessType.UPDATE)
	@PostMapping(value = "/save")
	public @ResponseBody Object save(ApiAppModel app) {
		try {
			app.setAppOwner(getUser().getYhm());
			appService.saveApp(app);
			return successStatus("I99001");
    	} catch (Exception e) {
			logException(this, e);
			return failStatus("I99002");
		}
	}
	
	/**
	 * 
	 * @description	： 删除应用
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:19:53
	 * @param model
	 * @param appId
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N060501, ywmc = "删除应用", czms = "删除一个应用", czlx = BusinessType.DELETE)
	@PostMapping("/delete/{appId}")
	public @ResponseBody Object deleteApp(@PathVariable("appId") String appId) {
		try {
			
			appService.deleteApp(appId);
			return successStatus("I99005");
    	} catch (Exception e) {
			logException(this, e);
			return failStatus("I99006");
		}
	}
	
	/**
	 * 
	 * @description	： 下拉框数据，用户所有应用
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:20:50
	 * @param app
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N060501, ywmc = "应用数据下拉框", czms = "根据用户查询用户所有的应用信息", czlx = BusinessType.SELECT)
	@GetMapping("/comboListByOwner")
	public @ResponseBody Object comboListByOwner(ApiAppModel app) {
		app.setAppOwner(getUser().getYhm());
		List<ApiAppModel> items = appService.getModelList(app);
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (ApiAppModel apiApp : items) {
			mapList.add(apiApp.toMap());
		}
		return mapList;
	}
	
	/**
	 * 
	 * @description	： 接口申请时选择应用
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:21:19
	 * @param app
	 * @param apiId
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0605, ywmc = "接口申请时选择应用", czms = "接口申请时选择应用", czlx = BusinessType.SELECT)
	@PostMapping("/applyTableData")
	public @ResponseBody Object applyTableData(ApiAppModel app) {
		app.setAppOwner(getUser().getYhm());
		QueryModel queryModel = app.getQueryModel();
		List<ApiAppModel> items = appService.getPagedApplyTableData(app);
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (ApiAppModel apiApp : items) {
			mapList.add(apiApp.toMap());
		}
		queryModel.setItems(mapList);
		return queryModel;
	}
}
