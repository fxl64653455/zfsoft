/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiMetircAnalysisService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiMetircService;
import com.zfsoft.webmvc.controller.BaseController;

/**
 * @className ： ApiMetricController
 * @description ： 接口监控数据查询Controller: 仅分页查询历史数据
 * @author ：万大龙（743）
 * @date ： 2017年11月27日 下午4:48:03
 * @version V1.0
 */
@Controller
@RequestMapping("/manager/api/metric/")
public class ApiMetricController extends BaseController {

	@Autowired
	private IApiMetircService metircService;
	@Autowired
	private IApiMetircAnalysisService metircAnalysisService;

	/**
	 * 
	 * @description ： 接口监控数据查询主界面;支持嵌入业务系统
	 * @author ： 万大龙（743）
	 * @date ：2017年11月28日 上午10:29:43
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/index", produces = "text/html; charset=UTF-8")
	public String index(@RequestParam(name = "inner", required = false) String inner, ModelMap model) {

		ApiMetircModel apiModel = new ApiMetircModel();
		model.addAttribute("appList", getMetircAnalysisService().appList(apiModel));
		model.addAttribute("bizList", getMetircAnalysisService().bizList(apiModel));
		model.addAttribute("deviceList", getMetircAnalysisService().deviceList(apiModel));
		model.addAttribute("osList", getMetircAnalysisService().osList(apiModel));
		model.addAttribute("browserList", getMetircAnalysisService().browserList(apiModel));
		
		return "html/api/metric/index";
	}

	@PostMapping(value = "list")
	@ResponseBody
	public Object list(ApiMetircModel model, HttpServletRequest request) throws Exception {
		try {
			QueryModel queryModel = model.getQueryModel();
			queryModel.setItems(getMetircService().getPagedList(model));
			return queryModel;
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}

	public IApiMetircService getMetircService() {
		return metircService;
	}

	public void setMetircService(IApiMetircService metircService) {
		this.metircService = metircService;
	}

	public IApiMetircAnalysisService getMetircAnalysisService() {
		return metircAnalysisService;
	}

	public void setMetircAnalysisService(IApiMetircAnalysisService metircAnalysisService) {
		this.metircAnalysisService = metircAnalysisService;
	}

}
