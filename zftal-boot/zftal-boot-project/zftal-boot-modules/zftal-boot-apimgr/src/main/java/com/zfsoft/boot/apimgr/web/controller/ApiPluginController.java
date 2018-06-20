/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiPluginModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiPluginService;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.web.dto.ApiPluginDto;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/plugin/")
public class ApiPluginController extends BaseController {

	@Autowired
	private IApiPluginService apiPluginService;
	
	@PostMapping(value = "list")
	@ResponseBody
	public Object list(String id, String type, HttpServletRequest request) throws ServletException {
		try {

			if (StringUtils.isEmpty(id)) {
				return new QueryModel();
			}

			ApiPluginModel pluginModel = new ApiPluginModel();
			pluginModel.setId(id);
			pluginModel.setType(type);
			pluginModel.setOwner(getUser().getYhm());

			QueryModel queryModel = pluginModel.getQueryModel();
			queryModel.setItems(getApiPluginService().getPagedList(pluginModel));
			return queryModel;
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}

	@RequestMapping(value = "to/new-plugin", produces = "text/html; charset=UTF-8")
	public String toNewPlugin(@RequestParam(value = "id") String id, HttpServletRequest request, Model model)
			throws Exception {

		return "html/api/plugin/new-plugin";

	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口插件", czms = "上传新接口插件", czlx = BusinessType.INSERT)
	@RequestMapping("new-plugin")
	@ResponseBody
	public Object newPlugin(@Valid ApiPluginDto pluginDto, @RequestParam(value = "iconbyte") MultipartFile file,
			HttpServletRequest request) {
		try {

			ApiPluginModel pluginModel = new ApiPluginModel();
			PropertyUtils.copyProperties(pluginModel, pluginDto);
			// 发布人
			pluginModel.setOwner(getUser().getYhm());
			  

			return successStatus("I10014");
		} catch (Exception e) {
			logException(this, e);
			return failStatus("I10015");
		}
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口插件", czms = "更新接口插件", czlx = BusinessType.UPDATE)
	@RequestMapping(value = "re-plugin")
	@ResponseBody
	public Object plugin(@RequestParam(value = "ids") String id, @Valid ApiPluginModel pluginModel,
			HttpServletRequest request) {
		if (StringUtils.isNotBlank(id)) {
			ApiPluginModel pluginModel2 = getApiPluginService().getModel(id);
			if (pluginModel2 != null) {
				if (StringUtils.equals("1", pluginModel2.getStatus())) {
					return failStatus("I10020");
				} else {
					pluginModel2.setStatus("1");
					getApiPluginService().update(pluginModel2);
					return failStatus("I10019");
				}
			} else {
				return failStatus("I10019");
			}
		} else {
			return failStatus("I10019");
		}
	}
	
	@PostMapping(value = "plugins")
	@ResponseBody
	public Object plugins(HttpServletRequest request) throws Exception {
		try {
			return getApiPluginService().getPlugins();
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}

	@PostMapping(value = "authzExtensions/{pluginId}")
	@ResponseBody
	public Object authzExtensions(@PathVariable("pluginId") String pluginId, HttpServletRequest request) throws Exception {
		try {
			return getApiPluginService().getAuthzExtensions(pluginId);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping(value = "apiExtensions/{pluginId}")
	@ResponseBody
	public Object apiExtensions(@PathVariable("pluginId") String pluginId, HttpServletRequest request) throws Exception {
		try {
			return getApiPluginService().getApiExtensions(pluginId);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}

	public IApiPluginService getApiPluginService() {
		return apiPluginService;
	}

	public void setApiPluginService(IApiPluginService apiPluginService) {
		this.apiPluginService = apiPluginService;
	}
	
}
