/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.logbiz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.logbiz.service.svcinterface.IBizLogService;
import com.zfsoft.boot.logbiz.web.dto.BizLogDto;
import com.zfsoft.webmvc.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @className ： BizLogController
 * @description ： TODO(描述这个类的作用)
 * @author ：万大龙（743）
 * @date ： 2017年11月13日 下午2:27:40
 * @version V1.0
 */
@Api(tags = "系统管理 : 业务日志")
@Controller
@RequestMapping("/xtgl/log")
public class BizLogController extends BaseController {

	@Autowired
	private IBizLogService bizLogService;

	public IBizLogService getBizLogService() {
		return bizLogService;
	}

	public void setBizLogService(IBizLogService bizLogService) {
		this.bizLogService = bizLogService;
	}
	
	@ApiIgnore
	@GetMapping("/biz")
	public String index() {
		return "html/xtgl/log/biz";
	}
	
	@ApiOperation(value = "业务操作日志", notes = "分页查询业务操作日志", httpMethod = "POST")
	@PostMapping(value = "/list")
	@ResponseBody 
	public Object list(BizLogDto par) {
		QueryModel queryModel = par.getQueryModel();
		queryModel.setItems(bizLogService.getPagedSearchList(par));
		return queryModel;
	}
	
}