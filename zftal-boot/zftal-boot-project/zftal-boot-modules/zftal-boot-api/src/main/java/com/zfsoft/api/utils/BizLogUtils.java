package com.zfsoft.api.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.spring.aop.BizLogBuilder;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.api.web.session.User;
import com.zfsoft.log4j2.Markers;
import com.zfsoft.log4j2.utils.Log4jUtils;

import freemarker.template.TemplateException;

/**
 * 
 * @className	： BizLogUtils
 * @description	： 业务日志记录工具：
 * 1、AOP进行拦截，调用Log4j2 Api接口进行日志记录
 * 2、Log4j2 配置基于数据库的日志存储配置和实现
 * @author 		：万大龙（743）
 * @date		： 2017年9月29日 下午6:39:02
 * @version 	V1.0
 */
public class BizLogUtils {
	
	private static Logger LOG = LoggerFactory.getLogger(BizLogUtils.class);
	
	public static void log(User user, BusinessLog businessLog, Map<String,Object> data) throws UnknownHostException, TemplateException, IOException {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, businessLog, data).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, businessLog, data).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, businessLog, data).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, businessLog, data).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		} catch (TemplateException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * 记录什么人，对什么业务 ,作了什么操作
	 * @param user 			用户对象
	 * @param modelKey 		模块名称KEY
	 * @param businessKey	 业务名称KEY
	 * @param czlx 			操作类型
	 * @param czms 			操作描述
	 */
	public static void log(User user,String modelKey, String businessKey, BusinessType czlx, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, modelKey, businessKey, czlx, czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, modelKey, businessKey, czlx, czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, modelKey, businessKey, czlx, czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, modelKey, businessKey, czlx, czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * 日志记录（查询）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void select(User user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.SELECT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.SELECT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.SELECT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.SELECT.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * 日志记录（查询）
	 * 
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void select(String ywmc, String mkmc, String czms) {
		User user = WebContext.getUser();
		select(user, ywmc, mkmc,czms);
	}

	/**
	 * 日志记录（删除）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void delete(User user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.DELETE.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.DELETE.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.DELETE.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.DELETE.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * 日志记录（删除）
	 * 
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void delete(String ywmc, String mkmc, String czms) {
		User user = WebContext.getUser();
		delete(user, ywmc, mkmc,czms);
	}

	/**
	 * 日志记录（修改）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void update(User user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.UPDATE.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.UPDATE.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.UPDATE.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.UPDATE.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * 日志记录（修改）
	 * 
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void update(String ywmc, String mkmc, String czms) {
		User user = WebContext.getUser();
		update(user, ywmc, mkmc, czms);
	}

	/**
	 * 日志记录（增加）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void insert(User user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.INSERT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.INSERT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.INSERT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.INSERT.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * 日志记录（增加）
	 * 
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void insert(String ywmc, String mkmc, String czms) {
		User user = WebContext.getUser();
		insert(user, ywmc, mkmc,czms);
	}

	/**
	 * 日志记录（登陆）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void login(User user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * 日志记录（登陆）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void login(String user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()){
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGIN.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * 日志记录（注销）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void logout(User user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * 日志记录（注销）
	 * 
	 * @param user 用户对象
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czms 操作描述
	 */
	public static void logout(String user, String ywmc, String mkmc, String czms) {
		try {
			if(Log4jUtils.isInfoEnabled()) {
				Log4jUtils.info(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isDebugEnabled()) {
				Log4jUtils.debug(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isWarnEnabled()) {
				Log4jUtils.warn(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}else if(Log4jUtils.isErrorEnabled()) {
				Log4jUtils.error(Markers.DB,BizLogBuilder.get().log(user, ywmc, mkmc, BusinessType.LOGOUT.getKey(), czms).build().toJSONString());
			}
		} catch (UnknownHostException e) {
			LOG.error(e.getMessage());
		}
	}

}
