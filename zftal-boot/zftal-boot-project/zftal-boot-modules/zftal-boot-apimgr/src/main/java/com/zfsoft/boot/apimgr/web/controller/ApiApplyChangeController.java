/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyChangeModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiAuditModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiApplyChangeService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiApplyService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiAuditService;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.web.dto.ApiApplyDto;
import com.zfsoft.io.utils.IOUtils;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/manager/api/apply/change/")
public class ApiApplyChangeController extends BaseController{
	
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private IApiApplyService apiApplyService;
	@Autowired
	private IApiApplyChangeService apiApplyChangeService;
	@Autowired
	private IApiAuditService apiAuditService;
	
	@PostMapping(value = "new-change/{deployId}/{applyId}", produces = "text/html; charset=UTF-8")
	public String apply(@PathVariable String deployId,@PathVariable String applyId,ModelMap model) {
		model.addAttribute("deployId", deployId);model.addAttribute("applyId", applyId);
		model.addAttribute("apply", apiApplyService.getModel(applyId));
		model.addAttribute("authType", oauth.getAuthType().name());
		return "html/api/apply/new-apply";
	}
	
	@BusinessLog(czmk = Czmk.N060505, ywmc = "保存变更申请记录", czms = "保存变更申请记录", czlx = BusinessType.INSERT)
	@PostMapping("save")
	public @ResponseBody Object save(@Valid ApiApplyDto par,BindingResult result) {
		if(result.hasErrors()) {
			String str = "";
			List<ObjectError> errorList = result.getAllErrors();
            for(ObjectError error : errorList){
            	str += error.getDefaultMessage() + ";";
            }
            return ResultUtils.statusMap(STATUS_ERROR, str);
		}
		try {
			apiApplyChangeService.saveApplyChange(par, getUser().getYhm());
			return ResultUtils.statusMap(STATUS_SUCCESS, "申请成功,等待审核!");
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	@BusinessLog(czmk = Czmk.N060510, ywmc = "变更申请列表", czms = "我的接口变更申请列表", czlx = BusinessType.SELECT)
	@PostMapping("list")
	public @ResponseBody Object applyData(ApiApplyChangeModel change) {
		try {
			change.setApplyUser(getUser().getYhm());
			QueryModel queryModel = change.getQueryModel();
			List<ApiApplyChangeModel> items = apiApplyChangeService.getPagedList(change);
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (ApiApplyChangeModel item : items) {
				mapList.add(item.toMap());
			}
			Map<String, Object> res = new HashMap<>();
			res.put("rows", mapList);res.put("total", queryModel.getTotalCount());
			return res;
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}
	
	@BusinessLog(czmk = Czmk.N060510, ywmc = "变更申请审核列表", czms = "我的变更申请审核列表", czlx = BusinessType.SELECT)
	@PostMapping("changeList")
	public @ResponseBody Object auditData(ApiApplyChangeModel apply) {
		try {
			apply.setApplyUser(getUser().getYhm());
			QueryModel queryModel = apply.getQueryModel();
			List<ApiApplyChangeModel> items = apiApplyChangeService.getPagedAuditList(apply);
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (ApiApplyChangeModel item : items) {
				mapList.add(item.toMap());
			}
			Map<String, Object> res = new HashMap<>();
			res.put("rows", mapList);res.put("total", queryModel.getTotalCount());
			return res;
		} catch (Exception e) {
			logException(this, e);
			return errorStatus();
		}
	}
	
	@RequestMapping("/changeFile/{applyChangeId}")
	public @ResponseBody ResponseEntity<byte[]> downChangeFile(@PathVariable String applyChangeId, HttpServletResponse response, HttpServletRequest request){
 		if (!StringUtils.isEmpty(applyChangeId)) {
			byte[] applyFile = apiApplyChangeService.getModel(applyChangeId).getChangeFile();
			OutputStream out = null;
			try {
				response.setContentType("application/msword");
				out = response.getOutputStream();
				out.write(applyFile);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				IOUtils.closeOutput(out);
			}
 		}
		return null;
	}
	
	/**
	 * @description	： 变更审核表单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月26日 下午3:26:30
	 * @param applyChangeId
	 * @param model
	 * @return
	 */
	@PostMapping(value = "new-audit/{applyChangeId}", produces = "text/html; charset=UTF-8")
	public String auditForm(@PathVariable String applyChangeId,ModelMap model) {
		ApiApplyChangeModel par = new ApiApplyChangeModel();
		par.setApplyChangeId(applyChangeId);
		model.addAttribute("change", apiApplyChangeService.getModel(par).toMap());
		return "html/api/audit/new-change";
	}
	
	/**
	 * @description	： 变更审核操作
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月31日 下午2:59:23
	 * @param audit
	 * @param applyChangeId
	 * @return
	 */
	@BusinessLog(czmk = Czmk.N0605, ywmc = "接口变更审核操作", czms = "接口变更审核操作", czlx = BusinessType.INSERT)
	@RequestMapping("audit/save")
	public @ResponseBody Object save(ApiAuditModel audit) {
		try {
			audit.setAuditUser(getUser().getYhm());
			apiAuditService.changeAudit(audit);
			return ResultUtils.statusMap(STATUS_SUCCESS, "审核成功!");
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
}
