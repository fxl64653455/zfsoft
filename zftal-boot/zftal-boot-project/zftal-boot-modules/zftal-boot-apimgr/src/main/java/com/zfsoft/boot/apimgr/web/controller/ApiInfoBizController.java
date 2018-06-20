/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInfoService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiPluginService;
import com.zfsoft.boot.apimgr.setup.builder.BizApiDescBuilder;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.util.enums.ApiTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.BizApiTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.DeployTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.RequestMethodEnum;
import com.zfsoft.boot.apimgr.util.enums.TopicNameEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoBizDto;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/info/biz/")
@SuppressWarnings("unchecked")
public class ApiInfoBizController extends BaseController {

	@Autowired
	private IApiInfoService apiInfoService;
	@Autowired
	private IApiPluginService apiPluginService;
	@Autowired
	private IApiDeployService apiDeployService;
	@Autowired
	private ApiOutputHandler apiOutputHandler;

	/**
	 * 业务系统列表首页
	 * 
	 * @author yangbilin
	 * @param request
	 * @param model
	 * @return String
	 */
	@GetMapping(value = "index", produces = "text/html; charset=UTF-8")
	public String index(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("typeList", DeployTypeEnum.typeList());
		return "html/api/info/biz/index";
	}

	/**
	 * 业务系统列表
	 * 
	 * @author yangbilin
	 * @param request
	 * @param model
	 * @return Object
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "业务系统接口", czms = "查询业务系统接口", czlx = BusinessType.SELECT)
	@PostMapping(value = "list")
	@ResponseBody
	public Object list(ApiInfoModel infoModel, HttpServletRequest request) throws Exception {
		try {
			QueryModel queryModel = infoModel.getQueryModel();

			infoModel.setType(ApiTypeEnum.BIZ.getKey());
			infoModel.setOwner(getUser().getYhm());
			
			List<ApiInfoBizDto> dtoList = new ArrayList<ApiInfoBizDto>();
			List<ApiInfoModel> items = getApiInfoService().getPagedList(infoModel);
			if (CollectionUtils.isNotEmpty(items)) {
				for (ApiInfoModel apiInfoModel : items) {
					dtoList.add(getApiOutputHandler().outputBiz(apiInfoModel));
				}
			}
			queryModel.setItems(dtoList);
			return queryModel;
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}

	/**
	 * 业务系统增加页面
	 * 
	 * @author yangbilin
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "to/new-biz-api", produces = "text/html; charset=UTF-8")
	public String toNewbizApi(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("typeList", BizApiTypeEnum.typeList());
		model.addAttribute("methodList", RequestMethodEnum.methodList());
		model.addAttribute("pluginList", getApiPluginService().getPlugins());
		model.addAttribute("topics", TopicNameEnum.topicList());
		return "html/api/info/biz/new-biz-api";
	}

	/**
	 * 业务系统增加
	 * 
	 * @author yangbilin
	 * @param request
	 * @param infoDto
	 * @param result
	 * @return Object
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "业务系统接口", czms = "新增业务系统接口", czlx = BusinessType.INSERT)
	@RequestMapping("new/form")
	@ResponseBody
	public Object newBizApi(@Valid ApiInfoBizDto infoDto, HttpServletRequest request, BindingResult result) {
		try {

			if (result.hasErrors()) {
				return ResultUtils.statusMap(STATUS_FAIL, result.getFieldError().getDefaultMessage());
			}

			ApiInfoModel infoModel = new ApiInfoModel();
			PropertyUtils.copyProperties(infoModel, infoDto);
			infoModel.setOwner(getUser().getYhm());
			infoModel.setType(ApiTypeEnum.BIZ.getKey());
			
			BizApiDescBuilder builder = BizApiDescBuilder.get()
					.input(infoDto.getType(),infoDto.getMethod(), infoDto.getUrl(), infoDto.getNamespace(), infoDto.getOperName(), infoDto.getParamList())
					.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
					.rocketmq(infoDto.getTopic(), infoDto.getTag());
			
			if(StringUtils.hasText(infoDto.getPluginId()) && StringUtils.hasText(infoDto.getExtensionId())) {
				builder.authz(infoDto.getPluginId(), infoDto.getExtensionId());
			}
			
			builder.errors(infoDto.getErrorList());
			infoModel.setDesc(builder.build());

			int res = getApiInfoService().insertInfo(infoModel);
			if (res > 0) {
				return successStatus("I10004");
			} else {
				return failStatus("I10005");
			}
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}

	@RequestMapping(value = "to/edit-biz-api", produces = "text/html; charset=UTF-8")
	public String editbiz(@RequestParam(value = "id") String id, HttpServletRequest request, Model model) throws Exception {

		ApiInfoModel rtModel = getApiInfoService().getModel(id);
		if (rtModel != null) {

			ApiInfoBizDto infoDto = getApiOutputHandler().outputBiz(rtModel);
			
			model.addAttribute("typeList", BizApiTypeEnum.typeList());
			model.addAttribute("infoDto", infoDto);
			model.addAttribute("methodList", RequestMethodEnum.methodList());
			model.addAttribute("pluginList", getApiPluginService().getPlugins());
			if(StringUtils.hasText(infoDto.getPluginId())) {
				model.addAttribute("extensionList", getApiPluginService().getAuthzExtensions(infoDto.getPluginId()));
			}
			model.addAttribute("topics", TopicNameEnum.topicList());
			
		}
		return "html/api/info/biz/edit-biz-api";
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "业务系统接口", czms = "编辑业务系统接口", czlx = BusinessType.UPDATE)
	@RequestMapping("edit/form")
	@ResponseBody
	public Object editForm(@Valid ApiInfoBizDto infoDto, HttpServletRequest request, BindingResult result) throws Exception {
		try {
			if (result.hasErrors()) {
				return ResultUtils.statusMap(STATUS_FAIL, result.getFieldError().getDefaultMessage());
			}

			ApiInfoModel infoModel = new ApiInfoModel();
			PropertyUtils.copyProperties(infoModel, infoDto);
			infoModel.setOwner(getUser().getYhm());
			infoModel.setType(ApiTypeEnum.BIZ.getKey());
			
			BizApiDescBuilder builder = BizApiDescBuilder.get()
					.input(infoDto.getType(),infoDto.getMethod(), infoDto.getUrl(), infoDto.getNamespace(), infoDto.getOperName(), infoDto.getParamList())
					.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
					.rocketmq(infoDto.getTopic(), infoDto.getTag());
			
			if(StringUtils.hasText(infoDto.getPluginId()) && StringUtils.hasText(infoDto.getExtensionId())) {
				builder.authz(infoDto.getPluginId(), infoDto.getExtensionId());
			}
			
			builder.errors(infoDto.getErrorList());
			infoModel.setDesc(builder.build());
			
			boolean flag = getApiInfoService().update(infoModel);
			if (flag) {
				return successStatus("I10006");
			} else {
				return failStatus("I10007");
			}
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}

	/**
	 * 
	 * @description ： 删除业务系统接口
	 * @author ： 万大龙（743）
	 * @date ：2017年10月23日 下午2:57:44
	 * @param ids
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "业务系统接口", czms = "删除业务系统接口", czlx = BusinessType.DELETE)
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(@RequestParam(value = "ids") String ids) throws Exception {
		if (StringUtils.isEmpty(ids)) {
			return failStatus("I00002");
		}
		Set<String> idArr = new HashSet<>();
		String[] strs = ids.split(",");
		for (String id : strs) {
			idArr.add(id);
		}
		apiInfoService.deleteApiInfo(idArr);
		return successStatus("I99005");
	}
	
	@RequestMapping(value = "to/detail/{id}", produces = "text/html; charset=UTF-8")
	public String detail(@PathVariable(value = "id") String id, HttpServletRequest request, Model model) throws Exception {

		ApiInfoModel rtModel = getApiInfoService().getModel(id);
		if (rtModel != null) {

			ApiInfoBizDto infoDto = getApiOutputHandler().outputBiz(rtModel);

			model.addAttribute("typeList", BizApiTypeEnum.typeList());
			model.addAttribute("infoDto", infoDto);
			model.addAttribute("methodList", RequestMethodEnum.methodList());
			model.addAttribute("pluginList", getApiPluginService().getPlugins());
			if(StringUtils.hasText(infoDto.getPluginId())) {
				model.addAttribute("extensionList", getApiPluginService().getAuthzExtensions(infoDto.getPluginId()));
			}
			
		}
		return "html/api/info/biz/detail";
	}


	/**
	 * 
	 * @description ： 判断接口是否可编辑
	 * @author ： 万大龙（743）
	 * @date ：2017年10月23日 下午3:47:31
	 * @param id
	 * @return
	 */
	@RequestMapping("editable")
	@ResponseBody
	public Object editable(@RequestParam(value = "id") String ids) {
		if (StringUtils.isEmpty(ids)) {
			return failStatus("I00002");
		}
		List<String> list = CollectionUtils.arrayToList(StringUtils.tokenizeToStringArray(ids));
		// 查询要删除的数据对应的依赖
		List<Map<String, String>> dependencies = getApiInfoService().getDependencies(list);
		// 不为空，则表示有数据在被使用
		if (!CollectionUtils.isEmpty(dependencies)) {
			StringBuilder builder = new StringBuilder("存在未解除的依赖关系：【");
			for (Map<String, String> hashMap : dependencies) {
				builder.append(hashMap.get("DEPLOY_ADDR")).append(" >> ").append(hashMap.get("API_NAME")).append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append("】,");
			return ResultUtils.statusMap(STATUS_FAIL, builder.toString());
		}
		return ResultUtils.statusMap(STATUS_SUCCESS, "");
	}
	
	public IApiInfoService getApiInfoService() {
		return apiInfoService;
	}

	public void setApiInfoService(IApiInfoService apiInfoService) {
		this.apiInfoService = apiInfoService;
	}

	public IApiPluginService getApiPluginService() {
		return apiPluginService;
	}

	public void setApiPluginService(IApiPluginService apiPluginService) {
		this.apiPluginService = apiPluginService;
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
