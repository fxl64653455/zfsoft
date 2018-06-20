/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiUsecaseModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiUsecaseService;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.util.enums.LanguageTypeEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiUsecaseDto;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/usecase/")
public class ApiUsecaseController extends BaseController {

	@Autowired
	private IApiUsecaseService apiUsecaseService;
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月26日 上午9:48:59
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "to/setting", produces = "text/html; charset=UTF-8")
    public String toUsecase(@RequestParam(value="id") String id,HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("languageList", LanguageTypeEnum.languages());
    	return "html/api/usecase/setting";
    }
	
	/**
	 * 
	 * @description	： 接口发布
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月21日 下午12:57:01
	 * @param deployDto
	 * @param file
	 * @param request
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0601, ywmc = "接口发布", czms = "创建接口使用示例", czlx = BusinessType.INSERT)
	@RequestMapping("setting")
	@ResponseBody
	public Object newDeploy(@Valid ApiUsecaseDto dto, HttpServletRequest request) {
		try {
			
			ApiUsecaseModel model = new ApiUsecaseModel();
			
			model.setId(dto.getId());
			model.setDeployId(dto.getDeployId());
			@SuppressWarnings("unchecked")
			List<String> list = CollectionUtils.arrayToList(dto.getLanguage());
			model.setQueryList(list);
			getApiUsecaseService().insert(model);
			return successStatus("API-I100005");
		} catch (Exception e) {
			logException(this, e);
			return failStatus("API-I100006");
		}
	}
 
	public IApiUsecaseService getApiUsecaseService() {
		return apiUsecaseService;
	}

	public void setApiUsecaseService(IApiUsecaseService apiUsecaseService) {
		this.apiUsecaseService = apiUsecaseService;
	}
	
}
