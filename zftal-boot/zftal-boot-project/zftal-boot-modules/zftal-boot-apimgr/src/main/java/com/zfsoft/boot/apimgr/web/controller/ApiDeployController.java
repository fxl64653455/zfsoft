/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInvokeModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInfoService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInvokeService;
import com.zfsoft.boot.apimgr.setup.builder.BizApiDescBuilder;
import com.zfsoft.boot.apimgr.setup.builder.DataApiDescBuilder;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.util.enums.ApiTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.DeployTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.LanguageTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.RequestMethodEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoBizDto;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoDataDto;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/deploy/")
public class ApiDeployController extends BaseController {

	@Autowired
	private ApiOutputHandler apiOutputHandler;
	@Autowired
	private IApiDeployService deployService;
	@Autowired
	private IApiInfoService apiInfoService;
	@Autowired
	private IApiInvokeService apiInvokeService;

	@PostMapping(value = "list")
	@ResponseBody
	public Object list(String id, String type, HttpServletRequest request) throws ServletException {
		try {

			if (StringUtils.isEmpty(id)) {
				return new QueryModel();
			}

			ApiDeployModel deployModel = new ApiDeployModel();
			deployModel.setId(id);
			deployModel.setType(type);
			deployModel.setOwner(getUser().getYhm());

			QueryModel queryModel = deployModel.getQueryModel();
			queryModel.setItems(getDeployService().getPagedList(deployModel));
			return queryModel;
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}

	/**
	 * 
	 * @description ： 接口发布页面
	 * @author ： 万大龙（743）
	 * @date ：2017年10月21日 下午12:48:56
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "to/new-deploy", produces = "text/html; charset=UTF-8")
	public String toNewDeploy(@RequestParam(value = "id") String id, HttpServletRequest request, Model model)
			throws Exception {

		ApiInfoModel infoModel = getApiInfoService().getModel(id);

		if (ApiTypeEnum.DATA.getKey().equalsIgnoreCase(infoModel.getType())) {
			model.addAttribute("infoDto", getApiOutputHandler().outputData(infoModel));
		} else {
			model.addAttribute("infoDto", getApiOutputHandler().outputBiz(infoModel));
		}
		model.addAttribute("typeList", DeployTypeEnum.typeList());
		model.addAttribute("methodList", RequestMethodEnum.methodList());
		model.addAttribute("languageList", LanguageTypeEnum.languages());
		model.addAttribute("apiType", infoModel.getType());
		
		return "html/api/deploy/new-deploy";

	}

	/**
	 * 
	 * @description ： 接口发布
	 * @author ： 万大龙（743）
	 * @date ：2017年10月21日 下午12:57:01
	 * @param deployDto
	 * @param file
	 * @param request
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口发布", czms = "保存并发布接口", czlx = BusinessType.INSERT)
	@RequestMapping("new-deploy")
	@ResponseBody
	public Object newDeploy(@Valid ApiDeployDto deployDto, @RequestParam(value = "iconbyte",required = false) MultipartFile file,
			HttpServletRequest request) {
		try {

			ApiDeployModel deployModel = new ApiDeployModel();
			PropertyUtils.copyProperties(deployModel, deployDto);
			// 发布人
			deployModel.setOwner(getUser().getYhm());
			// 根据接口类型添加不同的前缀
			deployModel.setAddr(DeployTypeEnum.valueOfIgnoreCase(deployDto.getType()).getPrefix() + deployDto.getAddr());
			/**
			 * 获取业务系统接口信息id
			 */
			ApiInfoModel infoModel = getApiInfoService().getModel(deployDto.getApiId());
			deployModel.setApi(infoModel);

			ApiInvokeModel invoke = apiInvokeService.getModel(deployDto.getApiId());
			
			if (ApiTypeEnum.BIZ.getKey().equalsIgnoreCase(infoModel.getType())) {

				ApiInfoBizDto infoDto = getApiOutputHandler().outputBiz(infoModel);
				
				BizApiDescBuilder builder =  BizApiDescBuilder.get()
						.input(infoDto.getType(), infoDto.getMethod(), infoDto.getUrl(), infoDto.getNamespace(), infoDto.getOperName(), infoDto.getParamList())
						.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
						.namespace(deployDto.getNamespace()).tags(deployDto.getTags()).usecases(deployDto.getLanguage())
						.rocketmq(infoDto.getTopic(), infoDto.getTag());
				
				if(StringUtils.hasText(infoDto.getPluginId()) && StringUtils.hasText(infoDto.getExtensionId())) {
					builder.authz(infoDto.getPluginId(), infoDto.getExtensionId());
				}
				
				if(invoke != null) {
					builder.invoke(invoke.getApiId(),invoke.getInvokeDeployId(),invoke.getParamRelation());
				}
				
				builder.errors(infoDto.getErrorList());
				
				deployModel.setDesc(builder.build());

			} else if (ApiTypeEnum.DATA.getKey().equalsIgnoreCase(infoModel.getType())) {

				ApiInfoDataDto infoDto = getApiOutputHandler().outputData(infoModel);

				DataApiDescBuilder builder = DataApiDescBuilder.get()
						.data(infoDto.getDbid(), infoDto.getExchType(), infoDto.getExchMethod(), infoDto.getTable(), infoDto.getSql())
						.rocketmq(infoDto.getTopic(), infoDto.getTag())
						.input(infoDto.getParamList(), infoDto.getUpdateList())
						.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
						.namespace(deployDto.getNamespace())
						.tags(deployDto.getTags())
						.pageable(deployDto.isPageable())
						.usecases(deployDto.getLanguage());
				
				if(invoke != null) {
					builder.invoke(invoke.getApiId(),invoke.getInvokeDeployId(),invoke.getParamRelation());
				}
				
				deployModel.setDesc(builder.build());

			}

			/**
			 * 计算该业务系统接口发布的版本信息
			 */
			String ver = getVersion(deployDto.getApiId());
			deployModel.setVer(ver);

			/**
			 * 获取发布接口图标
			 */
			// MultipartHttpServletRequest multipartRequest
			// =(MultipartHttpServletRequest)request;
			// MultipartFile file = multipartRequest.getFile("file");
//			if (file == null || file.isEmpty()) {
//				return failStatus("I10013");
//			}
			if(file != null && !file.isEmpty()) {
				deployModel.setIconbyte(file.getBytes());
			}

			getDeployService().newDeploy(deployModel);
			return successStatus("I10014");
		} catch (Exception e) {
			logException(this, e);
			return failStatus("I10015");
		}
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "业务系统接口", czms = "下线业务系统接口", czlx = BusinessType.UPDATE)
	@RequestMapping(value = "un-deploy")
	@ResponseBody
	public Object undeploy(@RequestParam(value = "ids") String id, @Valid ApiDeployModel deployModel,
			HttpServletRequest request) {
		if (StringUtils.isNotBlank(id)) {
			ApiDeployModel deployModel2 = getDeployService().getModel(id);
			if (deployModel2 != null) {
				if (StringUtils.equals("0", deployModel2.getStatus())) {
					return failStatus("I10016");
				} else {
					deployModel2.setStatus("0");
					boolean flag = getDeployService().update(deployModel2);
					if (flag) {
						boolean undeplogFlag = getDeployService().undeploy(deployModel2);
						if (undeplogFlag) {
							return successStatus("I10017");
						} else {
							return failStatus("I10018");
						}
					} else {
						return failStatus("I10018");
					}
				}
			} else {
				return failStatus("I10019");
			}
		} else {
			return failStatus("I10019");
		}
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "业务系统接口", czms = "重新上线业务系统接口", czlx = BusinessType.UPDATE)
	@RequestMapping(value = "re-deploy")
	@ResponseBody
	public Object deploy(@RequestParam(value = "ids") String id, @Valid ApiDeployModel deployModel,
			HttpServletRequest request) {
		if (StringUtils.isNotBlank(id)) {
			ApiDeployModel deployModel2 = getDeployService().getModel(id);
			if (deployModel2 != null) {
				if (StringUtils.equals("1", deployModel2.getStatus())) {
					return failStatus("I10020");
				} else {
					deployModel2.setStatus("1");
					boolean flag = getDeployService().update(deployModel2);
					if (flag) {
						boolean deplogFlag = getDeployService().redeploy(deployModel2);
						if (deplogFlag) {
							return successStatus("I10021");
						} else {
							return failStatus("I10022");
						}
					} else {
						return failStatus("I10022");
					}
				}
			} else {
				return failStatus("I10019");
			}
		} else {
			return failStatus("I10019");
		}
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口发布", czms = "接口上线", czlx = BusinessType.UPDATE)
	@RequestMapping(value = "online")
	@ResponseBody
	public Object online(@RequestParam(value = "id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ApiDeployModel deployModel = getDeployService().getModel(id);
			getDeployService().online(deployModel);
			return successStatus("I10014");
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口发布", czms = "接口下线", czlx = BusinessType.UPDATE)
	@RequestMapping(value = "offline")
	@ResponseBody
	public Object offline(@RequestParam(value = "id") String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			ApiDeployModel deployModel = getDeployService().getModel(id);
			getDeployService().offline(deployModel);
			return successStatus("I10017");
		} catch (Exception e) {
			logException(this, e);
			return failStatus("I10018");
		}
	}

	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口发布", czms = "接口删除", czlx = BusinessType.DELETE)
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(@RequestParam(value = "id") String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			ApiDeployModel deployModel = getDeployService().getModel(id);
			getDeployService().delete(deployModel);
			return successStatus("I10026");
		} catch (Exception e) {
			logException(this, e);
			return failStatus("I10027");
		}
	}
	
	/**
	 * 判断发布接口地址是否已经存在
	 * 
	 * @author yangbilin
	 * @param addr
	 * @return boolean
	 */
	@RequestMapping("addr/check")
	@ResponseBody
	public boolean checkAddr(@RequestParam(value = "type") String type,@RequestParam(value = "addr") String addr) {
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(addr)) {
			int flag = getDeployService().checkAddr(DeployTypeEnum.valueOfIgnoreCase(type).getPrefix() + addr);
			if (flag > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 计算该业务系统接口发布的版本信息
	 * 
	 * @author yangbilin
	 * @param infoid
	 * @return String
	 */
	private String getVersion(String infoid) {
		String versions = "V1.0";
		ApiDeployModel deployModel = getDeployService().getVersions(infoid);
		if (deployModel != null) {
			String version = deployModel.getVer().substring(1, deployModel.getVer().length());
			float ver = Float.parseFloat(version);
			versions = "V" + (ver + 1);
		}
		return versions;
	}

	/**
	 * 
	 * @description	： 下拉框数据，用户所有应用
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午6:20:50
	 * @param app
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口数据下拉框", czms = "根据用户查询用户发布的所有接口信息", czlx = BusinessType.SELECT)
	@GetMapping("/comboListByOwner")
	public @ResponseBody Object comboListByOwner(ApiDeployModel deploy) {
		deploy.setOwner(getUser().getYhm());
		List<ApiDeployModel> items = getDeployService().getComboList(deploy);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (ApiDeployModel d : items) {
			mapList.add(d.toMap());
		}
		return mapList;
	}
	
	public IApiDeployService getDeployService() {
		return deployService;
	}

	public void setDeployService(IApiDeployService deployService) {
		this.deployService = deployService;
	}

	public IApiInfoService getApiInfoService() {
		return apiInfoService;
	}

	public void setApiInfoService(IApiInfoService apiInfoService) {
		this.apiInfoService = apiInfoService;
	}

	public ApiOutputHandler getApiOutputHandler() {
		return apiOutputHandler;
	}

	public void setApiOutputHandler(ApiOutputHandler apiOutputHandler) {
		this.apiOutputHandler = apiOutputHandler;
	}

}
