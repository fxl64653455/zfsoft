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
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDatabaseService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInfoService;
import com.zfsoft.boot.apimgr.setup.builder.DataApiDescBuilder;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.util.enums.ApiTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.DataExchangeMethodEnum;
import com.zfsoft.boot.apimgr.util.enums.DataExchangeTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.DeployTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.TopicNameEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoDataDto;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;
import com.zfsoft.boot.apimgr.web.handler.DataApiExceptions;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/info/data/")
@SuppressWarnings("unchecked")
public class ApiInfoDataController extends BaseController {
	
	@Autowired
	private IApiDatabaseService apiDatabaseService;
	@Autowired
	private IApiInfoService apiInfoService;
	@Autowired
	private IApiDeployService apiDeployService;
	@Autowired
	private ApiOutputHandler apiOutputHandler;

	/**
	 * 
	 * @description ： 数据源接口功能主页
	 * @author ： 万大龙（743）
	 * @date ：2017年10月21日 下午1:40:23
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "index", produces = "text/html; charset=UTF-8")
	public String index(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("typeList", DeployTypeEnum.typeList());
		return "html/api/info/data/index";
	}

	/**
	 * 
	 * @description ： 数据源接口列表查询
	 * @author ： 万大龙（743）
	 * @date ：2017年10月21日 下午1:41:56
	 * @param infoModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "数据源接口", czms = "查询数据源接口", czlx = BusinessType.SELECT)
	@PostMapping(value = "list")
	@ResponseBody
	public Object list(ApiInfoModel infoModel, HttpServletRequest request) throws Exception {
		try {
			
			infoModel.setOwner(getUser().getYhm());
			infoModel.setType(ApiTypeEnum.DATA.getKey());
			
			QueryModel queryModel = infoModel.getQueryModel();
			List<ApiInfoDataDto> dtoList = new ArrayList<ApiInfoDataDto>();
			List<ApiInfoModel> items = getApiInfoService().getPagedList(infoModel);
			if (CollectionUtils.isNotEmpty(items)) {
				for (ApiInfoModel apiInfoModel : items) {
					dtoList.add(getApiOutputHandler().outputData(apiInfoModel));
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
	 * 
	 * @description ： 增加数据源接口界面
	 * @author ： 万大龙（743）
	 * @date ：2017年10月21日 下午1:45:26
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "to/new-data-api", produces = "text/html; charset=UTF-8")
	public String toNewDataApi(HttpServletRequest request, Model model) throws Exception {
		
		//查询当前用户的数据源记录
		model.addAttribute("dbList", getApiDatabaseService().getDatabaseList(getUser().getYhm()));
		model.addAttribute("exchangeTypeList", DataExchangeTypeEnum.exchangeTypeList());
		model.addAttribute("exchangeMethodList", DataExchangeMethodEnum.exchangeMethodList());
		
		model.addAttribute("errorList", DataApiExceptions.errors());
		model.addAttribute("topics", TopicNameEnum.topicList());
		
		return "html/api/info/data/new-data-api";
	}

	/**
	 * 
	 * @description	： 增加数据源接口信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月21日 下午1:47:49
	 * @param infoDto
	 * @param request
	 * @param result
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "数据源接口", czms = "新增数据源接口", czlx = BusinessType.INSERT)
	@RequestMapping("new/form")
	@ResponseBody
	public Object newDataApi(@Valid ApiInfoDataDto infoDto, HttpServletRequest request, BindingResult result) {
		try {
			
			ApiInfoModel infoModel = new ApiInfoModel();
			PropertyUtils.copyProperties(infoModel, infoDto);
			infoModel.setOwner(getUser().getYhm());
			infoModel.setType(ApiTypeEnum.DATA.getKey());
			
			String desc = DataApiDescBuilder.get()
					.data(infoDto.getDbid(), infoDto.getExchType(), infoDto.getExchMethod(), infoDto.getTable(), infoDto.getSql())
					.input( infoDto.getParamList(), infoDto.getUpdateList())
					.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
					.rocketmq(infoDto.getTopic(), infoDto.getTag()).build();
			infoModel.setDesc(desc);

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

	/**
	 * 业务系统编辑页面
	 * 
	 * @author yangbilin
	 * @param request
	 * @param model
	 * @param op
	 * @return String
	 */
	@RequestMapping(value = "to/edit-data-api", produces = "text/html; charset=UTF-8")
	public String editbiz(@RequestParam(value = "id") String id, HttpServletRequest request, Model model) throws Exception {

		ApiInfoModel rtModel = getApiInfoService().getModel(id);
		if (rtModel != null) {

			ApiInfoDataDto infoDto = getApiOutputHandler().outputData(rtModel);
			
			// 查询用户的数据源列表
			model.addAttribute("infoDto", infoDto);
			model.addAttribute("errorList", DataApiExceptions.errors());
			
			model.addAttribute("dbList", getApiDatabaseService().getDatabaseList(getUser().getYhm()));
			model.addAttribute("exchangeTypeList", DataExchangeTypeEnum.exchangeTypeList());
			model.addAttribute("exchangeMethodList", DataExchangeMethodEnum.exchangeMethodList());
			model.addAttribute("topics", TopicNameEnum.topicList());
			
		}
		return "html/api/info/data/edit-data-api";
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "数据源接口", czms = "编辑数据源接口", czlx = BusinessType.UPDATE)
	@RequestMapping("edit/form")
	@ResponseBody
	public Object editForm(@Valid ApiInfoDataDto infoDto, HttpServletRequest request,
			BindingResult result) throws Exception {
		try {
			if (result.hasErrors()) {
				return ResultUtils.statusMap(STATUS_FAIL, result.getFieldError().getDefaultMessage());
			}

			ApiInfoModel infoModel = new ApiInfoModel();
			PropertyUtils.copyProperties(infoModel, infoDto);
			infoModel.setOwner(getUser().getYhm());
			infoModel.setType(ApiTypeEnum.DATA.getKey());

			String desc = DataApiDescBuilder.get()
					.data(infoDto.getDbid(), infoDto.getExchType(), infoDto.getExchMethod(), infoDto.getTable(), infoDto.getSql())
					.input(infoDto.getParamList(), infoDto.getUpdateList())
					.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
					.rocketmq(infoDto.getTopic(), infoDto.getTag()).build();
			infoModel.setDesc(desc);
				 
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
	 * 业务系统删除
	 * 
	 * @author yangbilin
	 * @param ids
	 * @param request
	 * @return Object
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "数据源接口", czms = "删除数据源接口", czlx = BusinessType.DELETE)
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(@RequestParam(value = "ids") String ids, HttpServletRequest request) throws Exception{
		/**
		try {
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
				builder.append("】,无法删除.");
				return ResultUtils.statusMap(STATUS_FAIL, builder.toString());
			}
			//批量删除数据库配置记录
			getApiInfoService().batchDelete(list);
    		return successStatus("I99005");
    	} catch (Exception e) {
    		if(e instanceof BusinessCheckException) {
				throw e;
			}
			logException(this, e);
			return failStatus("I99006");
		}
		*/
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
			
			ApiInfoDataDto infoDto = getApiOutputHandler().outputData(rtModel);
			
			// 查询用户的数据源列表
			model.addAttribute("dbList", getApiDatabaseService().getDatabaseList(getUser().getYhm()));
			model.addAttribute("infoDto", infoDto);
			model.addAttribute("errorList", DataApiExceptions.errors());
			
		}
		return "html/api/info/data/detail";
	}
	
	/**
	 * 
	 * @description	： 查询指定数据的返回字段信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月21日 下午4:52:21
	 * @param apifor
	 * @return
	 *//*
    @PostMapping(value = "columns")
    @ResponseBody
	public Object sourceColumns(@Valid @RequestParam("apifor") String apifor) {
		try {
			
			ApiDatasourceDto dsDto = getApiOutputHandler().output(dsModel);
			List<Map<String,String>> filterColumnList = new ArrayList<Map<String,String>>();
			//数据表
			if(DataExchangeMethodEnum.TABLE.getKey().equals(dsDto.getMethod())) {
				
				//再次从数据库查询列信息，主要从安全方面考虑
				List<Map<String,String>> tableColumnList = getApiDatabaseService().getTableColumnList(dsModel.getDbname(), dsDto.getTable());
				for (Map<String, String> hashMap : tableColumnList) {
					String COLUMN_NAME = hashMap.get("COLUMN_NAME");
					if(dsDto.getColumns().containsKey(COLUMN_NAME)) {

						hashMap.put("COLUMN_ALIAS", dsDto.getAlias().get(COLUMN_NAME));
						
						filterColumnList.add(hashMap);
					}
				}
				
			} 
			// 数据查询SQL
			else {
				
				//再次从数据库查询列信息，主要从安全方面考虑
				List<Map<String, String>> sqlParserColumnList = null;
				try {
					sqlParserColumnList = getApiDatabaseService().getSQLParserColumnList(dsModel.getName(), StringEscapeUtils.unescapeHtml(dsDto.getSql()));
				} catch (Exception e) {
					e.printStackTrace();
					sqlParserColumnList = new ArrayList<Map<String, String>>();
				}
				for (Map<String, String> hashMap : sqlParserColumnList) {
					String COLUMN_NAME = hashMap.get("COLUMN_NAME");
					if(dsDto.getColumns().containsKey(COLUMN_NAME)) {

						hashMap.put("COLUMN_ALIAS", dsDto.getAlias().get(COLUMN_NAME));
						
						filterColumnList.add(hashMap);
					}
				}
				
			}
			
			
			return filterColumnList;
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}*/
    
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

	public IApiDatabaseService getApiDatabaseService() {
		return apiDatabaseService;
	}

	public void setApiDatabaseService(IApiDatabaseService apiDatabaseService) {
		this.apiDatabaseService = apiDatabaseService;
	}

	public IApiInfoService getApiInfoService() {
		return apiInfoService;
	}

	public void setApiInfoService(IApiInfoService apiInfoService) {
		this.apiInfoService = apiInfoService;
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
