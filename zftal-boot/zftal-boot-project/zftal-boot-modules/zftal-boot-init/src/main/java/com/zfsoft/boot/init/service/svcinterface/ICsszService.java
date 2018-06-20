package com.zfsoft.boot.init.service.svcinterface;

import java.util.Map;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.init.dao.entities.CsszModel;

/**
 * 
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：ZFTAL-参数设置
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a>
 * @version 2016年10月19日下午2:55:21
 */
public interface ICsszService extends BaseService<CsszModel>{
	
	/**
	 * 
	 * <p>方法说明：保存参数设置<p>
	 * <p>作者：a href="#">Zhangxiaobin[1036]<a><p>
	 * <p>时间：2016年12月26日下午3:54:43<p>
	 */
	public boolean saveCssz(CsszModel model, Map<String, String[]> parameters);
	
	/**
	 * 
	 *@描述		：更新系统参数设置
	 *@创建人		: wandalong
	 *@创建时间	: 2017年7月5日上午11:57:11
	 *@param key
	 *@param value
	 *@修改人	: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public void setValue(String key, String value);
	
	public void refreshCache();
	
	
}
