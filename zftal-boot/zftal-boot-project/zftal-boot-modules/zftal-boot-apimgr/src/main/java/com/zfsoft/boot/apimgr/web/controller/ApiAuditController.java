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
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiAuditModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiApplyService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiAuditService;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/audit/")
public class ApiAuditController extends BaseController{
	
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private IApiAuditService apiAuditService;
	@Autowired
	private IApiApplyService apiApplyService;

	@GetMapping(value = "index", produces = "text/html; charset=UTF-8")
	public String audit(ModelMap model) {
		model.addAttribute("authType", oauth.getAuthType().name());
		return "html/api/audit/index";
	}
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月16日 上午11:33:02
	 * @param apply
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N060510, ywmc = "接口审核列表", czms = "接口审核列表", czlx = BusinessType.SELECT)
	@PostMapping("list")
	public @ResponseBody Object auditData(ApiApplyModel apply) {
		try {
			apply.setApplyUser(getUser().getYhm());
			QueryModel queryModel = apply.getQueryModel();
			List<ApiApplyModel> items = apiApplyService.getPagedAuditList(apply);
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (ApiApplyModel item : items) {
				mapList.add(item.toMap());
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
	 * @description	： 审核表单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月10日 下午2:38:04
	 * @return
	 */
	@PostMapping(value = "new-audit/{applyId}/{appId}/{deployId}", produces = "text/html; charset=UTF-8")
	public String auditForm(@PathVariable("applyId")String applyId,@PathVariable("appId")String appId,@PathVariable("deployId")String deployId,ModelMap model) {
		model.addAttribute("applyId", applyId);
		model.addAttribute("appId", appId);
		model.addAttribute("deployId", deployId);
		model.addAttribute("apply", apiApplyService.getModel(applyId));
		return "html/api/audit/new-audit";
	}
	
	/**
	 * 
	 * @description	： 审核操作
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月10日 下午3:49:45
	 * @param audit
	 * @param appId
	 * @param deployId
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0605, ywmc = "接口审核操作", czms = "接口审核操作", czlx = BusinessType.INSERT)
	@RequestMapping("save")
	public @ResponseBody Object save(ApiAuditModel audit,String appId,String deployId) {
		try {
			audit.setAuditUser(getUser().getYhm());
			apiAuditService.audit(audit, appId, deployId);
			return ResultUtils.statusMap(STATUS_SUCCESS, "审核成功!");
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
}
