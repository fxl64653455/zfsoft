package com.zfsoft.boot.authz.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.web.session.User;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.basicutils.RandomUtils;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.UserModel;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzRoleService;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzUserService;
import com.zfsoft.search.core.SearchParser;
import com.zfsoft.security.algorithm.MD5Codec;
import com.zfsoft.webmvc.controller.BaseController;

/**
 * 
 * @className	： AuthzUserController
 * @description	： 权限管理：用户管理Controller
 * @author 		：万大龙（743）
 * @date		： 2017年11月6日 下午4:22:50
 * @version 	V1.0
 */
@Controller
@RequestMapping(value = "/authz/user")
public class AuthzUserController extends BaseController {

	@Autowired
	private IAuthzUserService userService;
	
	@Autowired
	private IAuthzRoleService roleService;
	
	/**
	 * 
	 * <p>方法说明：查询用户信息<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2016年9月2日下午3:07:23<p>
	 * @return String
	 */
	@RequiresPermissions("yhgl:cx")
	@RequestMapping(value = "/cxYhxx")
	public String cxYhxx(String gnmkdm,ModelMap model) {
		model.addAttribute("gnmkdm", gnmkdm);
		return "html/authz/user/cxYhxx";
	}

	
	/**
	 * 
	 * <p>方法说明：ajax加载用户信息<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2016年9月2日下午3:07:23<p>
	 * @param model
	 * @return JSONObject
	 */
	@RequiresPermissions("yhgl:cx")
	@RequestMapping(value = "/getYhxxList")
	@ResponseBody
	public Object getYhxxList(UserModel model){
		try {
			SearchParser.parseMybatisSQL(model.getSearchModel());
			QueryModel queryModel = model.getQueryModel();
			List<UserModel> pagedList = userService.getPagedList(model);
			queryModel.setItems(pagedList);
			return queryModel;
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	
	/**
	 * 
	 * <p>方法说明：访问增加用户<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月16日上午11:27:12<p>
	 * @return ModelAndView
	 */
	@RequiresPermissions("yhgl:zj")
	@RequestMapping(value = "/zjYhxx")
	public String zjYhxx(Model model) {
		String password = RandomUtils.genMixRandomStr(6);
		List<RoleModel> jsxxList = roleService.getModelList();
		model.addAttribute("password", password);
		model.addAttribute("jsxxList", jsxxList);
		return  "html/authz/user/zjYhxx";
	}

	/**
	 * 
	 * @description	： TODO
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:23:25
	 * @param model
	 * @param jsdms
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "新增用户-用户名：${model.yhm!}", ywmc = "用户管理", czlx = BusinessType.INSERT)
	@RequestMapping(value = "/saveYhxx")
	@RequiresPermissions("yhgl:zj")
	public @ResponseBody Object saveYhxx(UserModel model,String jsdms) {
		try {
			model.setMm(MD5Codec.encrypt(model.getMm()));
			if (!StringUtils.isEmpty(jsdms)){
				String [] jsdmArr = jsdms.split(",");
				userService.insert(model,jsdmArr);
			} else {
				userService.insert(model);
			}
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}
	
	/**
	 * @description	： 修改用户信息表单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月6日 下午5:55:41
	 * @param model
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "访问用户修改-用户名：${model.yhm!}", ywmc = "用户管理", czlx = BusinessType.SELECT)
	@RequiresPermissions("yhgl:xg")
	@RequestMapping(value = "/xgYhxx")
	public String xgYhxx(UserModel user,ModelMap model) {
		UserModel userModel = userService.getModel(user);
		List<RoleModel> jsxxList = roleService.getModelList();
		List<RoleModel> yhjsList = roleService.getJsxxListByZgh(user.getYhm());
		model.addAttribute("model", userModel);
		model.addAttribute("jsxxList", jsxxList);
		model.addAttribute("yhjsList", yhjsList);
		return "html/authz/user/xgYhxx";
	}
	
	/**
	 * @description	： 修改用户信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月6日 下午5:53:37
	 * @param model
	 * @param jsdms
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "修改用户-用户名：${model.yhm!}", ywmc = "用户管理", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequestMapping(value = "/modifyYhxx")
	@RequiresPermissions("yhgl:xg")
	public Object modifyYhxx(UserModel model,String jsdms) {
		try {
			if (!StringUtils.isEmpty(jsdms)){
				String [] jsdmArr = jsdms.split(",");
				userService.update(model,jsdmArr);
			} else {
				userService.update(model);
			}
			return successStatus("I99003");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99004");
		}
	}
	
	
	/**
	 * @description	： 删除用户信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:35:56
	 * @param ids
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "批量删除用户-用户名：${ids}", ywmc = "用户管理", czlx = BusinessType.DELETE)
	@RequestMapping(value = "/scYhxx")
	@RequiresPermissions("yhgl:sc")
	public @ResponseBody Object scYhxx(@RequestParam String ids) {
		try {
			userService.scYhxx(ids.split(","));
			return successStatus("I99005");
		}catch (Exception e) {
			logException(e);
			return failStatus("I99006");
		}
	}
	
	/**
	 * @description	： 查看用户信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:51:06
	 * @param user
	 * @param model
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "查看用户信息-用户名：${model.yhm!}", ywmc = "用户管理", czlx = BusinessType.SELECT)
	@RequestMapping(value = "/ckYhxx")
	@RequiresPermissions("yhgl:ck")
	public String ckYhxx(UserModel user, ModelMap model) {
		model.addAttribute("model", userService.getModel(user));
		return "html/authz/user/ckYhxx";
	}


	/**
	 * @description	： 访问修改密码
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:52:44
	 * @return
	 */
	@RequestMapping(value = "/xgMm")
	public String xgMm(){
		return "html/authz/user/xgMm";
	}
	
	
	/**
	 * @description	： 密码初始化
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:58:54
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("yhgl:mmcsh")
	@RequestMapping("/mmcsh")
	public String mmcsh(@RequestParam String ids,ModelMap model){
		model.addAttribute("ids", ids);
		return "html/authz/user/mmcsh";
	}
	
	/**
	 * 
	 * <p>方法说明：保存密码初始化<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月29日上午9:51:38<p>
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("yhgl:mmcsh")
	@RequestMapping("/saveMmcsh")
	public Object saveMmcsh(@RequestParam String ids ,@RequestParam String password){
		try {
			String[] yhmArr = ids.split(",");
			userService.updateYhmm(yhmArr, MD5Codec.encrypt(password));
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}
	
	/**
	 * @description	： 修改密码
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:55:23
	 * @param ymm
	 * @param mm
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "修改密码", ywmc = "用户管理", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequestMapping("/savePassword")
	public Object savePassword(@RequestParam String ymm, @RequestParam String mm){
		try {
			User user = getUser();
			UserModel model = new UserModel();
			model.setYhm(user.getYhm());
			model.setMm(MD5Codec.encrypt(ymm));
			UserModel yModel = userService.getModel(model);
			if (yModel == null){
				return failStatus("I99012");
			}
			model.setMm(MD5Codec.encrypt(mm));
			userService.update(model);
			return successStatus("I99003");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99004");
		}
	}
	
	/**
	 * @description	： 启用停用
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午10:03:45
	 * @param ids
	 * @param model
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "用户启用停用：${ids!}", ywmc = "用户管理", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequestMapping("/qyty")
	@RequiresPermissions(logical=Logical.OR,value={"yhgl:qy","yhgl:ty"})
	public Object qyty(@RequestParam String ids,UserModel model){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("yhzt", model.getYhzt());
			map.put("yhms", ids.split(","));
			userService.batchUpdate(map);
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}
	
}
