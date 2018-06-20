package com.zfsoft.boot.init.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.init.dao.entities.JcsjModel;
import com.zfsoft.boot.init.service.svcinterface.IJcsjService;
import com.zfsoft.search.core.SearchParser;
import com.zfsoft.webmvc.controller.BaseController;

import net.sf.json.JSONObject;

/**
 * 
 * 
 * 类名称：JcsjAction 
 * 类描述： 基础数据控制 
 * 创建人：xucy 
 * 创建时间：2012-4-17 下午01:14:25 
 * 修改人：xucy
 * 修改时间：2012-4-17 下午01:14:25 
 * 修改备注：
 * 
 * @version
 * 
 */
@Controller
@RequestMapping("/xtgl/jcsj")
public class JcsjController extends BaseController {

	@Autowired
	private IJcsjService jcsjService;

	/**
	 * 
	 * 方法描述: 基础数据列表 参数 @return 参数说明 返回类型 String 返回类型
	 * 
	 * @throws
	 */
	@RequestMapping("/cxJcsj")
	@RequiresPermissions("jcsj:cx")
	public String cxJcsj(String gnmkdm,JcsjModel jcsj,ModelMap model) {
		model.addAttribute("gnmkdm", gnmkdm);
		model.addAttribute("lxdmList", jcsjService.getModelList(jcsj));
		return "html/init/jcsj/cxJcsj";
	}
	
	
	/**
	 * 
	 * <p>方法说明：ajax加载基础数据<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2016年9月7日上午9:01:46<p>
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("jcsj:cx")
	@RequestMapping("/getJcsjList")
	public Object getJcsjList(JcsjModel model){
		try {
			SearchParser.parseMybatisSQL(model.getSearchModel());
			List<JcsjModel> pagedList = jcsjService.getPagedList(model);
			
			QueryModel queryModel = model.getQueryModel();
			queryModel.setItems(pagedList);
			return queryModel;
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	
	/**
	 * 
	 * 方法描述: 增加基础数据 参数 @return 参数说明 返回类型 String 返回类型
	 * 
	 * @throws
	 */
	@RequestMapping("/zjJcsj")
	@RequiresPermissions("jcsj:zj")
	public String zjJcsj(JcsjModel jcsj,ModelMap model) {
        model.addAttribute("lxdmList", jcsjService.getModelList(jcsj));
        return "html/init/jcsj/zjJcsj";
	}

	
	
	/**
	 * 
	 * <p>方法说明：保存基础数据<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2016年8月25日下午4:37:18<p>
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "新增基础数据-类型:${model.lxdm}代码:${model.dm}名称:${model.mc}", ywmc = "基础数据", czlx = BusinessType.INSERT)
	@ResponseBody
	@RequiresPermissions("jcsj:zj")
	@RequestMapping("/saveJcsj")
	public Object saveJcsj(JcsjModel model) {
		try {
			model.setXtjb("yw");
			if(StringUtils.isBlank(model.getZt())){
				model.setZt("1");
			}
			jcsjService.insert(model);
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}

	/**
	 * 
	 * 方法描述: 修改基础数据 参数 @return 参数说明 返回类型 String 返回类型
	 * 
	 * @throws
	 */
	@RequiresPermissions("jcsj:xg")
	@RequestMapping("/xgJcsj")
	public String xgJcsj(JcsjModel jcsj,ModelMap model) {
		model.addAttribute("lxdmList", jcsjService.getModelList(jcsj));
		model.addAttribute("model", jcsjService.getModel(jcsj));
		return "html/init/jcsj/xgJcsj";
	}

	
	
	/**
	 * 
	 * <p>方法说明：修改基础数据<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2016年8月25日下午4:38:38<p>
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "修改基础数据-ID:${pkValue}", ywmc = "基础数据", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequiresPermissions("jcsj:xg")
	@RequestMapping("/modifyJcsj")
	public Object modifyJcsj(@RequestParam(required=true)String pkValue,JcsjModel model) {
		try {
			model.setPkValue(pkValue);
			jcsjService.update(model);
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}
	

	/**
	 * 
	 * 方法描述: 删除基础数据 参数 @return 参数说明 返回类型 String 返回类型
	 * 
	 * @throws
	 */
	@BusinessLog(czmk = "系统管理", czms = "删除基础数据-ID:${ids}", ywmc = "基础数据", czlx = BusinessType.DELETE)
	@ResponseBody
	@RequiresPermissions("jcsj:sc")
	@RequestMapping("/scJcsj")
	public Object scJcsj(@RequestParam String ids,
			JcsjModel model) throws Exception {
		try {
			model.setPkValue(ids);
			int[] result = jcsjService.scJcsj(model);
			JSONObject json = new JSONObject();
			String message;
			if(result[0] == 0){
				message = getMessage("I99017",new Object[]{result[0],result[1]});
				json.put("status", "fail");
			}else if(result[1] == 0){
				message = getMessage("I99005");
				json.put("status", "success");
			}else {
				message = getMessage("I99016",new Object[]{result[0],result[1]});
				json.put("status", "success");
			}
			json.put("message", message);
			return json;
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}

	/**
	 * 
	 * 方法描述: 验证代码是否已经存在 参数 @return 参数说明 返回类型 String 返回类型
	 * 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/valideDm")
	public boolean valideDm(JcsjModel model) throws Exception {
		
		// 查询单个用户信息
		JcsjModel jcsjModel = jcsjService.getModel(model);

		return null == jcsjModel;
	}

}
