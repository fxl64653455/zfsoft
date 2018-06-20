package com.zfsoft.boot.init.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basicutils.TimeUtils;
import com.zfsoft.boot.init.dao.daointerface.IXtszDao;
import com.zfsoft.boot.init.dao.entities.XtszModel;
import com.zfsoft.boot.init.service.svcinterface.IXtszService;
import com.zfsoft.license.LicenseOps;

/**
 * 
* 
* 类名称：XtszServiceImpl 
* 类描述： 系统设置 实现
* 创建人：qph 
* 创建时间：2012-4-20
* 修改备注： 
*
 */
@Service("xtszService")
public class XtszServiceImpl extends BaseServiceImpl<XtszModel, IXtszDao> implements IXtszService {
	
	private static final Logger log = LoggerFactory.getLogger(XtszServiceImpl.class);
	
	@Resource
	private IXtszDao dao;	
	
	private LicenseOps licensOps = LicenseOps.getInstance();
	
	@Override
	public void afterPropertiesSet() throws Exception {
	    super.setDao(dao);   
	}
	

	@Cacheable(value="ZFTAL_BASIC",key="'CONFIG_XXDM'")
	@Override
	public String getXXDM() {
		try {
			if(/*licensOps.isNativeLibLoadSuccess() &&*/ licensOps.isLicenseOpsEnabled()){
				return licensOps.getAuthUserCode();
			}
			
			log.info("由于授权模块加载失败或者未启动,学校代码信息无法获取!");
			
			if(log.isDebugEnabled()){
//				log.debug("学校代码无法获取的原因是,库文件加载情况：{}, 是否启用了授权模块：{}", new Object[]{licensOps.isNativeLibLoadSuccess(),licensOps.isLicenseOpsEnabled()});
				log.debug("学校代码无法获取的原因是, 是否启用了授权模块：{}", new Object[]{licensOps.isLicenseOpsEnabled()});
			}
		} catch (Exception e) {
			log.error("", e);
			e.printStackTrace();
		}
		return null;
	}

	@Cacheable(value="ZFTAL_BASIC",key="'CONFIG_XXMC'")
	@Override
	public String getXXMC() {
		try {
			if(/*licensOps.isNativeLibLoadSuccess() && */licensOps.isLicenseOpsEnabled()){
				String authUser = licensOps.getAuthUser();
				if(authUser != null){
					return new String(new Base64().decode(authUser),"utf-8");
				}
			}
			if(log.isDebugEnabled()){
//				log.debug("学校名称无法获取的原因是,库文件加载情况：{}, 是否启用了授权模块：{}", new Object[]{licensOps.isNativeLibLoadSuccess(),licensOps.isLicenseOpsEnabled()});
				log.debug("学校名称无法获取的原因是, 是否启用了授权模块：{}", new Object[]{licensOps.isLicenseOpsEnabled()});
			}
		} catch (Exception e) {
			log.error("", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>方法说明：获取系统设置<p>
	 * <p>作者：a href="#">Zhangxiaobin[1036]<a><p>
	 * <p>时间：2016年6月24日下午2:43:55<p>
	 */
	@Override
	public XtszModel getModel(String nope){
		XtszModel model = super.getModel("");
		if(model == null){
			model = new XtszModel();
		}
		
		model.setXxdm(getXXDM());
		model.setXxmc(getXXMC());
		return model;
	}
	
	/**
	 * @see  {@link com.zfsoft.comp.xtgl.xtsz.service.XtszService#cxNdlb()}.
	 */
	@Override
	public List cxNdlb(String... params) {
		List list = new ArrayList();
		long year = Long.parseLong(TimeUtils.getYear()) ;
		for (int i = -5; i < 5; i++) {
			Map param = new HashMap();

			String text = String.valueOf(year + i);
			param.put("text", text);
			param.put("value", text);
			list.add(param);
		}

		return list;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.zfsoft.service.svcinterface.IXtszService#getNdList()
	 */
	@Override
	public List<Map<String, String>> getNdList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		long year = Long.parseLong(TimeUtils.getYear()) ;
		for (int i = -5; i < 5; i++) {
			Map<String, String> param = new HashMap<String, String>();
			
			param.put("text", String.valueOf(year + i));
			param.put("value", String.valueOf(year + i));
			list.add(param);
		}
		
		return list;
	}

	/**
	 * @see  {@link com.zfsoft.comp.xtgl.xtsz.service.XtszService#cxXnlb()}.
	 */
	@Override
	public List cxXnlb(String... params){
		List list = new ArrayList();
		long year = Long.parseLong(TimeUtils.getYear()) ;
		
		for (int i = -5; i < 5; i++) {
			Map param = new HashMap();
			
			String text = (year + i) + "-" + (year + i + 1);
			param.put("text", text);
			param.put("value", text);
			list.add(param);
		}

		return list;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.zfsoft.service.svcinterface.IXtszService#getXnList()
	 */
	@Override
	public List<Map<String, String>> getXnList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		long year = Long.parseLong(TimeUtils.getYear()) ;
		
		for (int i = -5; i < 5; i++) {
			Map<String, String> param = new HashMap<String, String>();
			
			String text = (year + i) + "-" + (year + i + 1);
			param.put("dm", text);
			param.put("mc", text);
			list.add(param);
		}

		return list;
	}

	
}
