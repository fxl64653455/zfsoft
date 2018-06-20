/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.api.spring.aop;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.annotation.BusinessType;
import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.api.web.session.User;
import com.zfsoft.basicutils.BlankUtils;
import com.zfsoft.basicutils.DateUtils;
import com.zfsoft.basicutils.net.InetAddressUtils;
import com.zfsoft.freemarker.utils.FormatUtils;

import freemarker.template.TemplateException;

public class BizLogBuilder implements Builder<JSONObject> {

	/**
	 * 操作日志信息
	 */
	private JSONObject desc;
	
	private BizLogBuilder() {
		desc = new JSONObject();
	}
	
	/**
	 * 
	 * @description	： 记录业务操作日志
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月29日 下午7:00:37
	 * @param user
	 * @param businesslog
	 * @param data
	 * @return
	 * @throws IOException 
	 * @throws TemplateException 
	 * @throws UnknownHostException 
	 */
	public BizLogBuilder log(User user, BusinessLog businesslog, Map<String,Object> data) throws TemplateException, UnknownHostException, IOException{
		return this.log(user, businesslog.ywmc(), businesslog.czmk(), businesslog.czlx().getKey(), FormatUtils.toTextStatic(data, businesslog.czms()));
	}
	
	/**
	 * 记录什么人，对什么业务 ,作了什么操作
	 * @param user 			操作人
	 * @param modelKey 		模块名称KEY
	 * @param bizKey	 业务名称KEY
	 * @param czlx 			操作类型
	 * @param czms 			操作描述
	 */
	public BizLogBuilder log(User user,String modelKey, String bizKey, BusinessType czlx, String czms) throws UnknownHostException {
		
		//功能模块
		String modelName = MessageUtil.getText(modelKey);
			   modelName = StringUtils.hasText(modelName) ? modelName : modelKey;
		//业务模块
		String business = MessageUtil.getText(bizKey);
			   business = StringUtils.hasText(business) ? business : bizKey;
			   
		StringBuilder build = new StringBuilder();
		//操作描述
		//build.delete(0, build.length());
		build.append("用户[").append(user.getYhm()).append("(").append(user.getXm()).append(")]<br/>");
		build.append("在[").append(modelName).append("]功能模块;<br/>[");
		build.append(business).append("]业务模块;<br/>进行[");
		build.append(czlx.toString().split(":")[1]).append("]操作;<br/>");
		if(!BlankUtils.isBlank(czms)){
			build.append(czms);
		}
		
		return this.log(user, business, modelName, czlx, build.toString());
	}
	
	/**
	 * 记录什么人，对什么业务 ,作了什么操作
	 * @param user 操作人
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czlx 操作类型
	 * @param czms 操作描述
	 * @throws UnknownHostException 
	 */
	public BizLogBuilder log(User user, String ywmc, String mkmc, String czlx, String czms) throws UnknownHostException {
		return this.log(String.format("%s（%s）", user.getXm(), user.getYhm()), ywmc, mkmc, czlx, czms);
	}
	
	/**
	 * 记录什么人，对什么业务 ,作了什么操作
	 * @param user 操作人
	 * @param ywmc 业务名称
	 * @param mkmc 模块名称
	 * @param czlx 操作类型
	 * @param czms 操作描述
	 * @throws UnknownHostException 
	 */
	public BizLogBuilder log(String user, String ywmc, String mkmc, String czlx, String czms) throws UnknownHostException {
		
		// 业务类型
		desc.put("czlx", czlx);
		// 业务名称
		desc.put("ywmc", ywmc);
		// 操作模块
		desc.put("czmk", mkmc);
		// 操作描述
		desc.put("czms", czms);
		// 操作日期
		desc.put("czrq", DateUtils.format(DateUtils.DATE_FORMAT_TWO));
		// 操作人
		desc.put("czr", user);
		// 访问IP地址
		desc.put("remote", WebContext.getRemoteAddr());
		// 记录日志主机IP
		try {
			desc.put("host", InetAddressUtils.getHostAddressIp());
		} catch (Exception e) {
			desc.put("host", "localhost");
		}
		
		return this;
	}
	
	public static BizLogBuilder get() {
		return new BizLogBuilder();
	}
	
	@Override
	public JSONObject build() {
		return desc;
	}
	
}
