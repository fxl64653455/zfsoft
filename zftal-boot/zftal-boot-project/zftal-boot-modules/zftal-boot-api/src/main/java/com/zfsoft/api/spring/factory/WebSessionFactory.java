package com.zfsoft.api.spring.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;

import com.zfsoft.api.web.session.User;
import com.zfsoft.web.Parameter;
import com.zfsoft.web.Parameters;
import com.zfsoft.web.context.WebContext;


/**
 * 
 *@类名称	: WebSessionFactory.java
 *@类描述	：会话工厂：只能用于当前线程，EJB端不支持使用（以后可能支持）
 *@创建人	：wandalong
 *@创建时间	：Mar 17, 2016 5:20:47 PM
 *@修改人	：
 *@修改时间	：
 *@版本号	:v1.0
 */
public class WebSessionFactory{
	
	protected WebSessionFactory() {

	}
	 
	/**
	 * 取当前会话用户信息
	 * @return User
	 */
	public static User getUser() {
		try {
			//获取Session中的User对象，如果没有获取到，则认为使用了Shiro权限控制组件
			HttpSession session = WebContext.getSession();
			User user = (User) session.getAttribute(Parameters.getGlobalString(Parameter.SESSION_USER_KEY));
			if(null != user){
				return user;
			}
			//获取Shiro权限控制组件中的User对象
			if(SecurityUtils.getSubject().getPrincipals() == null){
				return null;
			}
			return (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取请求上下文路径
	 * @return
	 */
	public static String getContextPath() {
		HttpServletRequest request = (HttpServletRequest) WebContext.getRequest();
		if (request == null) {
			return "xsgzgl";
		}
		return request.getContextPath();
	}

}
