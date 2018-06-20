package com.zfsoft.boot.notice.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.ZFMessage;
import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.utils.BaseDataReader;
import com.zfsoft.api.web.session.User;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.basicutils.DateUtils;
import com.zfsoft.boot.notice.dao.entities.XwglModel;
import com.zfsoft.boot.notice.service.svcinterface.IXwglService;
import com.zfsoft.search.core.SearchParser;
import com.zfsoft.webmvc.controller.BaseController;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @className	： XwglController
 * @description	： 新闻管理
 * @author 		：万大龙（743）
 * @date		： 2018年4月10日 下午2:46:56
 * @version 	V1.0
 */
@ApiIgnore
@Controller
@RequestMapping(value = "/notice/management/")
public class XwglController extends BaseController{
	
	@Autowired
	protected IXwglService xwglService;
	
	/**
	 * 发布对象、是否列表
	 */
	public void setValueStack(HttpServletRequest request){
		List<HashMap<String, String>> fbdxList = BaseDataReader.getCachedOptionList("fbdx");
		request.setAttribute("fbdxList", fbdxList);

		List<HashMap<String, String>> sfList = BaseDataReader.getCachedOptionList("isNot");
		request.setAttribute("sfList", sfList);
	}
	
	
	/**
	 * 
	* 方法描述: 新闻查询
	* 参数 @return 参数说明
	* 返回类型 String 返回类型
	 */
	@RequiresPermissions("xwgl:cx")
	@RequestMapping(value = "/cxXw")
	public String cxXw(HttpServletRequest request, XwglModel model) {
		try {
			setValueStack(request);
			return "/globalweb/comp/xtgl/xwgl/cxXw";
		} catch (Exception e) {
			logException(e);
			return ERROR_VIEW;
		}
	}
	
	@ResponseBody
	@RequiresPermissions("xwgl:cx")
	@RequestMapping(value = "/getXwxxList")
	public Object getXwxxList(XwglModel model){
		try {
			SearchParser.parseMybatisSQL(model.getSearchModel());
			QueryModel queryModel = model.getQueryModel();
			List<XwglModel> pagedList = getXwglService().getPagedList(model);
			queryModel.setItems(pagedList);
			return queryModel;
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}

	/**
	 * 
	* 方法描述: 请求增加新闻页面
	* @return
	 */
	@RequiresPermissions("xwgl:zj")
	@RequestMapping(value = "/zjXw")
	public String zjXw(HttpServletRequest request, XwglModel model) {
		try {
			// 生成初始化数据
			model.setSffb("1");
			model.setSfzd("0");
			model.setSfzy("0");
			model.setFbsj(DateUtils.format(DateUtils.DATE_FORMAT_TWO));
			setValueStack(request);
			request.setAttribute("model", model);
			return "/globalweb/comp/xtgl/xwgl/zjXw";
		} catch (Exception e) {
			logException(e);
			return ERROR_VIEW;
		}
	}
	
	/**
	 * 
	 * <p>方法说明：ajax保存新闻<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2016年8月25日下午3:20:16<p>
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "保存通知公告-标题：${model.xwbt}", ywmc = "通知公告", czlx = BusinessType.INSERT)
	@ResponseBody
	@RequiresPermissions("xwgl:zj")
	@RequestMapping(value = "/saveXw")
	public Object saveXw(HttpServletRequest request, XwglModel model) throws Exception {
		User user = getUser();
		model.setFbr(user.getYhm());
		boolean result = getXwglService().zjBcXw(model);
		ZFMessage key = result ? ZFMessage.SAVE_SUCCESS : ZFMessage.SAVE_FAIL;
		return key.status();
	}
	
	
	/**
	 * 修改新闻页面
	 * @return
	 */
	@RequestMapping(value = "/xgXw")
	@RequiresPermissions("xwgl:xg")
	public String xgXw(HttpServletRequest request, XwglModel model) {
		try {
			XwglModel xwglModel = getXwglService().getModel(model);
			request.setAttribute("model", xwglModel);
			setValueStack(request);
			request.setAttribute("fList", Arrays.asList(xwglModel.getFbdxs()));
			return "/globalweb/comp/xtgl/xwgl/xgXw";
		} catch (Exception e) {
			logException(e);
			return ERROR_VIEW;
		}
	}
	
	/**
	 * 
	 * <p>方法说明：ajax修改保存新闻<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2016年8月25日下午3:23:00<p>
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "修改通知公告-编号：${model.xwbh}", ywmc = "通知公告", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequiresPermissions("xwgl:xg")
	@RequestMapping(value = "/modifyXw")
	public Object modifyXw(HttpServletRequest request, XwglModel model) {
		try {
			boolean result = getXwglService().xgBcXw(model);
			ZFMessage key = result ? ZFMessage.MODIFY_SUCCESS : ZFMessage.MODIFY_FAIL;
			return key.status();
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	
	/**
	 * 查看新闻
	 * @return
	 */
	@RequiresPermissions("xwgl:ck")
	@RequestMapping(value = "/ckXw")
	public String ckXw(HttpServletRequest request, XwglModel model) {
		try {
			XwglModel xwglModel = getXwglService().getModel(model);
			request.setAttribute("model", xwglModel);
			return "/globalweb/comp/xtgl/xwgl/ckXw";
		} catch (Exception e) {
			logException(e);
			return ERROR_VIEW;
		}
	}	
	
	
	/**
	 * 删除新闻
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "删除通知公告-编号：${ids}", ywmc = "通知公告", czlx = BusinessType.DELETE)
	@ResponseBody
	@RequestMapping(value = "/scXw")
	@RequiresPermissions("xwgl:sc")
	public Object scXw(@RequestParam(required=true)String ids, XwglModel model) {
		try {
			boolean result = getXwglService().scXw(ids);
			ZFMessage key = result ? ZFMessage.DELETE_SUCCESS : ZFMessage.DELETE_FAIL;
			return key.status();
		} catch (Exception e) {
			logException(e);
			return ZFMessage.SYSTEM_ERROR;
		}
	}
	
	
	/**
	 * 发布新闻
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "发布通知公告-编号：${ids}", ywmc = "通知公告", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequestMapping(value = "/fbXw")
	@RequiresPermissions("xwgl:fb")
	public Object fbXw(@RequestParam(required=true)String ids, XwglModel model) {
		try {
			boolean result = getXwglService().xgFbxw(ids);
			ZFMessage key = result ? ZFMessage.DO_SUCCESS : ZFMessage.DO_FAIL;
			return key.status(new String[]{"发布"});
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	/**
	 * 取消发布新闻
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "取消发布通知公告-编号：${ids}", ywmc = "通知公告", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequestMapping(value = "/qxfbXw")
	@RequiresPermissions("xwgl:qxfb")
	public Object qxfbXw(@RequestParam(required=true)String ids, XwglModel model) {
		try {
			boolean result = getXwglService().xgQxfbxw(ids);
	         ZFMessage key = result ? ZFMessage.DO_SUCCESS : ZFMessage.DO_FAIL;
			 return key.status(new String[]{"取消发布"});
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	
	
	/**
	 * 新闻置顶
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "置顶通知公告-编号：${ids}", ywmc = "通知公告", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequestMapping(value = "/zdXw")
	@RequiresPermissions("xwgl:zd")
	public Object zdXw(@RequestParam(required=true)String ids, XwglModel model) {
		try {
			boolean result = getXwglService().xgZdxw(ids);
			 ZFMessage key = result ? ZFMessage.DO_SUCCESS : ZFMessage.DO_FAIL;
			 return key.status(new String[]{"置顶"});
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}
	
	/**
	 * 取消置顶新闻
	 * @return
	 */
	@BusinessLog(czmk = "系统管理", czms = "取消置顶通知公告-编号：${ids}", ywmc = "通知公告", czlx = BusinessType.UPDATE)
	@ResponseBody
	@RequestMapping(value = "/qxzdXw")
	@RequiresPermissions("xwgl:qxzd")
	public Object qxzdXw(@RequestParam(required=true)String ids, XwglModel model) {
		try {
			boolean result = getXwglService().xgQxzdXw(ids);
			ZFMessage key = result ? ZFMessage.DO_SUCCESS : ZFMessage.DO_FAIL;
			return key.status(new String[] { "置顶" });
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}


	public IXwglService getXwglService() {
		return xwglService;
	}

	public void setXwglService(IXwglService xwglService) {
		this.xwglService = xwglService;
	}
	
}
