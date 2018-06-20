/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.io.OutputStream;
import java.util.ArrayList;
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
import com.zfsoft.boot.apimgr.service.svcinterface.IApiApplyService;
import com.zfsoft.boot.apimgr.setup.data.LogType.Czmk;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.web.dto.ApiApplyDto;
import com.zfsoft.io.utils.IOUtils;
import com.zfsoft.webmvc.controller.BaseController;

/***
 * 
 * @className	： ApiApplyController
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年10月9日 上午9:35:42
 * @version 	V1.0
 */
@Controller	
@RequestMapping("/manager/api/apply/")
public class ApiApplyController extends BaseController{
	
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private IApiApplyService apiApplyService;
	
	@GetMapping(value = "index", produces = "text/html; charset=UTF-8")
	public String index(ModelMap model) {
		model.addAttribute("authType", oauth.getAuthType().name());
		return "html/api/apply/index";
	}

	/**
	 * 
	 * @description	： 接口申请
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午7:26:35
	 * @param apiId
	 * @param model
	 * @return
	 */
	@PostMapping(value = "new-apply/{deployId}", produces = "text/html; charset=UTF-8")
	public String apply(@PathVariable(value = "deployId") String deployId,ModelMap model) {
		model.addAttribute("deployId", deployId);
		model.addAttribute("authType", oauth.getAuthType().name());
		return "html/api/apply/new-apply";
	}
	
	@BusinessLog(czmk = Czmk.N060505, ywmc = "我的接口申请列表", czms = "我的接口申请列表", czlx = BusinessType.SELECT)
	@PostMapping("applyData")
	public @ResponseBody Object applyData(ApiApplyModel apply) {
		try {
			apply.setApplyUser(getUser().getYhm());
			QueryModel queryModel = apply.getQueryModel();
			List<ApiApplyModel> items = apiApplyService.getPagedList(apply);
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
	
	@BusinessLog(czmk = Czmk.N060505, ywmc = "保存申请记录", czms = "根据接口发布ID和应用ID串保存申请记录", czlx = BusinessType.INSERT)
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
			apiApplyService.saveApplys(par, getUser().getYhm());
			return ResultUtils.statusMap(STATUS_SUCCESS, "申请成功,等待审核!");
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	@RequestMapping("/applyFile/{applyId}")
	public @ResponseBody ResponseEntity<byte[]> showApplyFile(@PathVariable(value = "applyId") String applyId, HttpServletResponse response, HttpServletRequest request){
 		if (!StringUtils.isEmpty(applyId)) {
			byte[] applyFile = apiApplyService.getModel(applyId).getApplyFile();
			OutputStream out = null;
			try {
//				response.setHeader("Content-Disposition", "inline; filename=" );
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
	
	@BusinessLog(czmk = Czmk.N0605, ywmc = "接口申请下拉框", czms = "根据用户查询用户申请的所有接口", czlx = BusinessType.SELECT)
	@GetMapping("/comboListByOwner")
	public @ResponseBody Object comboListByOwner() {
		return apiApplyService.comboListByOwner(getUser().getYhm());
	}
}
