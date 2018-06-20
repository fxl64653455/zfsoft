package com.zfsoft.boot.notice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.notice.dao.daointerface.IXwglDao;
import com.zfsoft.boot.notice.dao.entities.XwglModel;
import com.zfsoft.boot.notice.service.svcinterface.IXwglService;

/**
 * 新闻管理实现
 * 
 * @className ： XwglServiceImpl
 * @description ： TODO(描述这个类的作用)
 * @author ：万大龙（743）
 * @date ： 2018年4月10日 下午2:57:43
 * @version V1.0
 */
@Service
public class XwglServiceImpl extends BaseServiceImpl<XwglModel, IXwglDao> implements IXwglService {

	/**
	 * @see {@link com.zfsoft.IXwglService.xtgl.xwgl.service.XwglService#xgFbxw(String)}.
	 */
	public boolean xgFbxw(String idStr) {

		if (!StringUtils.isEmpty(idStr)) {
			String[] ids = idStr.split(",");
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			for (int i = 0; i < ids.length; i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xwbh", ids[i]);
				list.add(map);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("list", list);
			param.put("sffb", 1);
			int result = dao.batchUpdate(param);
			return result > 0 ? true : false;
		}
		return false;
	}

	/**
	 * @see {@link com.zfsoft.IXwglService.xtgl.xwgl.service.XwglService#scXw(String)}.
	 */
	public boolean scXw(String idStr) {
		if (!StringUtils.isEmpty(idStr)) {
			String[] ids = idStr.split(",");

			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < ids.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xwbh", ids[i]);
				list.add(map);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("list", list);
			int result = dao.batchDelete(param);
			return result > 0 ? true : false;
		}
		return false;
	}

	/**
	 * @see {@link com.zfsoft.IXwglService.xtgl.xwgl.service.XwglService#xgQxfbxw(String)}.
	 */
	public boolean xgQxfbxw(String idStr) {

		if (!StringUtils.isEmpty(idStr)) {
			String[] ids = idStr.split(",");
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			for (int i = 0; i < ids.length; i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xwbh", ids[i]);
				list.add(map);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("list", list);
			param.put("sffb", 0);
			int result = dao.batchUpdate(param);
			return result > 0 ? true : false;
		}

		return false;
	}

	/**
	 * @see {@link com.zfsoft.IXwglService.xtgl.xwgl.service.XwglService#xgZdxw(String)}.
	 */
	public boolean xgZdxw(String idStr) {

		if (!StringUtils.isEmpty(idStr)) {
			String[] ids = idStr.split(",");
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			for (int i = 0; i < ids.length; i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xwbh", ids[i]);
				list.add(map);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("list", list);
			param.put("sfzd", 1);
			int result = dao.batchUpdate(param);
			return result > 0 ? true : false;
		}
		return false;
	}

	/**
	 * @see {@link com.zfsoft.IXwglService.xtgl.xwgl.service.XwglService#xgQxzdXw(String)}.
	 */
	public boolean xgQxzdXw(String idStr) {

		if (!StringUtils.isEmpty(idStr)) {
			String[] ids = idStr.split(",");
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			for (int i = 0; i < ids.length; i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xwbh", ids[i]);
				list.add(map);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("list", list);
			param.put("sfzd", 0);
			int result = dao.batchUpdate(param);
			return result > 0 ? true : false;
		}

		return false;
	}

	/**
	 * 增加保存新闻
	 * 
	 * @param model
	 * @return
	 */
	public boolean zjBcXw(XwglModel model) {
		if (model == null) {
			return false;
		}
		String fbdx = "";
		// 拼接发布对象
		if (model.getFbdxs() != null) {
			for (int i = 0; i < model.getFbdxs().length; i++) {
				if (!"".equals(fbdx)) {
					fbdx = fbdx + "," + model.getFbdxs()[i];
				} else {
					fbdx = model.getFbdxs()[i];
				}
			}
		}
		model.setFbdx(fbdx);
		return dao.insert(model) > 0;
	}

	/**
	 * 修改保存新闻
	 * 
	 * @param model
	 * @return
	 */
	public boolean xgBcXw(XwglModel model) {
		if (model == null) {
			return false;
		}
		String fbdx = "";
		// 拼接发布对象
		if (model.getFbdxs() != null) {
			for (int i = 0; i < model.getFbdxs().length; i++) {
				if (!"".equals(fbdx)) {
					fbdx = fbdx + "," + model.getFbdxs()[i];
				} else {
					fbdx = model.getFbdxs()[i];
				}
			}
		}
		model.setFbdx(fbdx);
		return dao.update(model) > 0;
	}

	/**
	 * 查询单条数据
	 * 
	 * @param t
	 * @return
	 */
	public XwglModel getModel(XwglModel model) {
		if (model == null) {
			return null;
		}
		XwglModel xwgl = dao.getModel(model);
		if (xwgl != null && !StringUtils.isEmpty(xwgl.getFbdx())) {
			xwgl.setFbdxs(xwgl.getFbdx().split(","));
		}
		return xwgl;
	}
	
}
