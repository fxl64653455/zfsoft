package com.zfsoft.boot.init.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.init.dao.entities.CsszModel;
import com.zfsoft.boot.init.service.svcinterface.ICsszService;
import com.zfsoft.webmvc.controller.BaseController;

/**
 * @className	： CsszController
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2018年6月11日 上午9:05:13
 * @version 	V1.0
 */
@Controller
@RequestMapping("/xtgl/xtsz")
public class CsszController extends BaseController {

	@Autowired
	private ICsszService csszService;

	@RequiresPermissions("cssz:cx")
	@RequestMapping("/cxCssz")
	public String cxCssz(String gnmkdm,ModelMap model){
		model.addAttribute("gnmkdm", gnmkdm);
		return "html/init/xtsz/cxCssz";
	}
	
	/**
	 * @description	： 获取参数设置
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月11日 上午9:05:24
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("cssz:cx")
	@RequestMapping("/getCsszList")
	public Object getCsszList(CsszModel model){
		try {
			QueryModel queryModel = model.getQueryModel();
			List<CsszModel> itemList = csszService.getModelList(model);
			queryModel.setItems(itemList);
			return queryModel;
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	
	/**
	 * @description	： 保存参数设置
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月11日 上午9:05:37
	 * @param request
	 * @param model
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "修改系统参数", ywmc = "参数设置", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequiresPermissions("cssz:bc")
	@RequestMapping(value = "/saveCssz")
	public Object saveCssz(HttpServletRequest request,CsszModel model) { 
		try {
//			List<CsszModel> itemList = csszService.getModelList(model);
//			for (CsszModel cssz : itemList){
//				
//				if (Integer.valueOf(cssz.getZdlx()) == 4){
//					//多选
//					String[] zdzArr = request.getParameterValues(cssz.getZdm());
//					CsszModel csszModel = new CsszModel();
//					csszModel.setZdm(cssz.getZdm());
//					String zdz = ArrayUtils.toString(zdzArr);
//					if (zdz.length() > 2){
//						csszModel.setZdz(zdz.substring(1, zdz.length()-1));
//						csszService.update(csszModel);
//					}
//				} else {
//					String zdz = request.getParameter(cssz.getZdm());
//					CsszModel csszModel = new CsszModel();
//					csszModel.setZdm(cssz.getZdm());
//					csszModel.setZdz(zdz);
//					csszService.update(csszModel);
//				}
//			}
			csszService.saveCssz(model, request.getParameterMap());
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}

}
