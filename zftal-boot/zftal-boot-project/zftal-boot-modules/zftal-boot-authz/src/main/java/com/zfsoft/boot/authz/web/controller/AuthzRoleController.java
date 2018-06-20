package com.zfsoft.boot.authz.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.authz.dao.entities.AncdModel;
import com.zfsoft.boot.authz.dao.entities.Menu;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.SjzyModel;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzRoleService;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzUserService;
import com.zfsoft.webmvc.controller.BaseDozerController;

/**
 * @className	： AuthzRoleController
 * @description	： 权限管理：角色管理Controller
 * @author 		：万大龙（743）
 * @date		： 2017年11月6日 下午4:22:36
 * @version 	V1.0
 */
@Controller
@RequestMapping(value = "/authz/role/")
public class AuthzRoleController extends BaseDozerController {

	@Autowired
	private IAuthzRoleService roleService;
	
	@Autowired
	private IAuthzUserService userService;
	
	/**
	 * @description	： 进入角色管理主页
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月11日 上午11:25:26
	 * @return
	 */
	@RequiresPermissions("role:cx")
	@RequestMapping(value = "index")
	public String index(String gnmkdm,ModelMap model) {
		model.addAttribute("gnmkdm", gnmkdm);
		return "/html/authz/role/index";
	}

	/**
	 * 
	 * @description	： 分页查询角色信息
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月10日 下午4:50:04
	 * @param model
	 * @return
	 */
	@PostMapping(value = "list")
	@RequiresPermissions("role:cx")
	@ResponseBody
	public Object getJsxxList(RoleModel model){
		try {
			QueryModel queryModel = model.getQueryModel();
			List<RoleModel> pagedList = getRoleService().getPagedList(model);
			queryModel.setItems(pagedList);
			return queryModel;
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	/**
	 * 
	 * @description	： 进入新增角色界面
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月10日 下午5:00:17
	 * @return
	 */
	@RequiresPermissions("role:zj")
	@RequestMapping(value = "to/new-role")
	public String toNewRole() {
		return "/html/authz/role/new-role";
	}
	
	@BusinessLog(czmk = "系统管理", czms = "新增角色-名称：${model.jsmc!}", ywmc = "角色管理", czlx = BusinessType.INSERT)
	@PostMapping(value = "new/form")
	@RequiresPermissions("role:zj")
	@ResponseBody
	public Object newRole(@Valid RoleModel role) throws Exception {
		role.setJslx("1");role.setYhm(getUser().getYhm());
		if(getRoleService().insert(role)) {
			return ResultUtils.statusMap(STATUS_SUCCESS, role.getJsdm());
		}
		return failStatus("I99002");
	}
	
	
	@BusinessLog(czmk = "系统管理", czms = "访问修改角色功能，角色代码：${model.jsdm!}", ywmc = "角色管理", czlx = BusinessType.SELECT)
	@RequiresPermissions("role:xg")
	@RequestMapping(value = "to/edit-role/{id}")
	public String editRole(@PathVariable String id, Model model) throws Exception {
		
		// 查询单个角色信息
		RoleModel roleModel = roleService.getModel(id);
		model.addAttribute("model", roleModel);
		
		return "/html/authz/role/xgJsxx";
	}
	
	/**
	 * @description	： 查看角色信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午2:56:40
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("role:ck")
	@RequestMapping(value = "/ckJsxx")
	public String ckJsxx(RoleModel role,ModelMap model) {
		model.addAttribute("model", roleService.getModel(role));
		return "html/authz/role/ckJsxx";
	}


	/**
	 * 
	 * @description	： 修改角色信息
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:15:30
	 * @param model
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "修改角色-名称：${model.jsmc!}", ywmc = "角色管理", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequiresPermissions("role:xg")
	@RequestMapping(value = "/modifyJsxx",method=RequestMethod.POST)
	public Object modifyJsxx(@Valid RoleModel model) {
		try {
			roleService.update(model);
			return successStatus("I99003");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99004");
		}
	}
	
	
	
	/**
	 * 
	 * @description	： 删除角色
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:15:24
	 * @param ids
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "删除角色-角色代码：${id!}", ywmc = "角色管理", czlx = BusinessType.DELETE)
	@ResponseBody
	@RequiresPermissions("role:sc")
	@RequestMapping(value = "/scJsxx")
	public Object scJsxx(RoleModel role)  {
		try {
			roleService.delete(role);
			return successStatus("I99005");
		}catch (Exception e) {
			logException(e);
			return failStatus("I99006");
		}
	}

	
	/**
	 * 
	 * @description	： 未分配用户列表
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:15:07
	 * @param jsdm
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("role:fpyh")
	@RequestMapping(value = "/getWfpyhList")
	public Object getWfpyhList(@RequestParam String jsdm)  {
		try {
			RoleModel model = new RoleModel();
        	model.setJsdm(jsdm);
			QueryModel queryModel = model.getQueryModel();
			queryModel.setItems(getUserService().getPagedWfpyhList(model));
			return queryModel;
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
		
	}
        
	/**
	 * 
	 * @description	： 已分配用户列表
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:15:14
	 * @param jsdm
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("role:fpyh")
	@RequestMapping(value = "/getYfpyhList")
    public Object getYfpyhList(@RequestParam String jsdm ){
        try {
        	RoleModel model = new RoleModel();
        	model.setJsdm(jsdm);
			QueryModel queryModel = model.getQueryModel();
	        queryModel.setItems(getUserService().getPagedYfpyhList(model));
	        return queryModel;
        } catch (Exception e) {
			logException(e);
			return errorStatus();
		}
    }
	
	
	@RequiresPermissions("role:fpyh")
	@RequestMapping(value = "/fpyh")
	public String fpyh(RoleModel role,ModelMap model) {
		model.addAttribute("model", roleService.getModel(role));
		return "html/authz/role/fpyh";
	}
	
	
	/**
	 * 
	 * @description	： 角色分配用户
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:21:36
	 * @param jsdm
	 * @param ids
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "角色【${jsdm}】分配用户【${ids}】", ywmc = "角色管理", czlx = BusinessType.INSERT)
	@ResponseBody
	@RequiresPermissions("role:fpyh")
	@RequestMapping(value = "/inertYhfp", method = { RequestMethod.GET, RequestMethod.POST})
	public Object inertYhfp(@RequestParam String jsdm ,@RequestParam String ids) {
		try {
			String[] yhms = ids.split(",");
			roleService.zjJsyhxx(jsdm, yhms);
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}
	
	
	
	/**
	 * 
	 * @description	： 取消分配用户
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:21:27
	 * @param jsdm
	 * @param ids
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "角色【${jsdm}】取消分配用户【${ids}】", ywmc = "角色管理", czlx = BusinessType.DELETE)
	@ResponseBody
	@RequiresPermissions("role:fpyh")
	@RequestMapping(value = "/deleteYhfp")
	public Object deleteYhfp(@RequestParam String jsdm ,@RequestParam String ids) {
		try {
			String[] yhms = ids.split(",");
			roleService.scJsyhxx(jsdm, yhms);
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}
	
	/**
	 * @description	： 角色功能授权
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午3:57:45
	 * @param jsdm
	 * @param model
	 * @return
	 */
	@RequiresPermissions("role:gnsq")
	@RequestMapping(value = "/gnsq")
	public String gnsq(@RequestParam String jsdm,ModelMap model){
		RoleModel role = new RoleModel();role.setJsdm(jsdm);
		List<Menu> gnqxList = roleService.getAllGnqxList();
		List<AncdModel> buttonList = roleService.getButtonList(jsdm);
		model.addAttribute("model", roleService.getModel(role));
		model.addAttribute("gnqxList", gnqxList);
		model.addAttribute("buttonList", buttonList);
		return "html/authz/role/gnsq";
	}
	
	/**
	 * 
	 * @description	： 保存角色功能授权
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:20:54
	 * @param jsdm 角色代码
	 * @param gnczid 操作ID
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "角色【${jsdm}】授权", ywmc = "角色管理", czlx = BusinessType.UPDATE)
	@RequiresPermissions("role:gnsq")
	@RequestMapping(value = "/saveGnqx")
	public @ResponseBody Object saveGnqx(@RequestParam String jsdm, String[] gnczid){	
		try {
			roleService.saveGnqx(jsdm, gnczid);
			return successStatus("I99001");
		} catch (Exception e) {
			logException(e);
			return failStatus("I99002");
		}
	}

	/**
	 * 
	 * <p>方法说明：数据授权<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月17日下午2:57:06<p>
	 * @param jsdm 角色代码
	 * @return ModelAndView
	 */
	@RequiresPermissions("role:sjsq")
	@RequestMapping(value = "/sjsq")
	public String sjsq(@RequestParam String jsdm,ModelMap model){
		RoleModel role = new RoleModel();role.setJsdm(jsdm);
		role = roleService.getModel(role);
		List<SjzyModel> sjzyList = roleService.getSjzyList();
		List<String> jsgzList = roleService.getSjqxByJsdm(jsdm);
		model.addAttribute("model", role);
		model.addAttribute("sjzyList", sjzyList);
		model.addAttribute("jsgzList", jsgzList);
		return "html/authz/role/sjsq";
	}
	
	public IAuthzRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IAuthzRoleService roleService) {
		this.roleService = roleService;
	}

	public IAuthzUserService getUserService() {
		return userService;
	}

	public void setUserService(IAuthzUserService userService) {
		this.userService = userService;
	}
	
}
