/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.authz.shiro.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import com.zfsoft.api.utils.BizLogUtils;
import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.shiro.filter.LoginListener;
import com.zfsoft.web.utils.WebRequestUtils;

public class ZFLoginListener implements LoginListener {

	/**
	 * 登录失败前操作
	 * @param token
	 * @param e
	 * @param request
	 * @param response
	 
	protected void beforeOnLoginFailure(ZFUsernamePasswordToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		
		String czmk = MessageUtil.getTextOnly("log.login");
		String ywmk = MessageUtil.getTextOnly("log.login.cx");
		String czms = MessageUtil.getTextOnly("log.login.ms");
		String czlx = BusinessType.LOGIN.getKey();
		
		BusinessLogModel log = new BusinessLogModel();
		log.setCzlx(czlx);
		log.setCzmk(czmk);
		log.setCzms(czms + "[登录失败,出现异常：" +e.getMessage() + "]");
		log.setYwmc(ywmk);
		log.setCzr(token.getPrincipal().toString());
		log.setCzrq(DateUtils.format(DateUtils.DATE_FORMAT_TWO));
		log.setIpdz(WebRequestUtils.getRemoteAddr((HttpServletRequest) request));
		log.setZjm(WebRequestUtils.getRemoteAddr((HttpServletRequest) request));
		LogEngineImpl.getInstance().log(log);
		
	}*/

	/**
	 * 成功登录前操作
	 * @param token
	 * @param subject
	 * @param request
	 * @param response
	 
	protected void beforeOnLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response){
		String czmk = MessageUtil.getTextOnly("log.login");
		String ywmk = MessageUtil.getTextOnly("log.login.cx");
		String czms = MessageUtil.getTextOnly("log.login.ms");
		String czlx = BusinessType.LOGIN.getKey();
		
		BusinessLogModel log = new BusinessLogModel();
		log.setCzlx(czlx);
		log.setCzmk(czmk);
		log.setCzms(czms);
		log.setYwmc(ywmk);
		log.setCzrq(DateUtils.format(DateUtils.DATE_FORMAT_TWO));
		log.setCzr(token.getPrincipal().toString());
		log.setIpdz(WebRequestUtils.getRemoteAddr((HttpServletRequest) request));
		log.setZjm(WebRequestUtils.getRemoteAddr((HttpServletRequest) request));
		LogEngineImpl.getInstance().log(log);
	}*/
	
	/**
	 * 
	 * @description	： 登录失败
	 * @author 		：万大龙（743）
	 * @date 		：2017年10月10日 下午4:32:41
	 * @param token
	 * @param ex
	 * @param request
	 * @param response
	 */
	@Override
	public void onLoginFailure(AuthenticationToken token, Exception ex, ServletRequest request,
			ServletResponse response) {

		String czmk = MessageUtil.getTextOnly("log.login");
		String ywmk = MessageUtil.getTextOnly("log.login.cx");
		String czms = MessageUtil.getTextOnly("log.login.ms");
		
		BizLogUtils.login(token.getPrincipal().toString(), ywmk, czmk, czms + "[登录失败,出现异常：" + ex.getMessage() + "]");
		
	}

	/**
	 * 
	 * @description	： 成功登录
	 * @author 		：万大龙（743）
	 * @date 		：2017年10月10日 下午4:32:18
	 * @param token
	 * @param subject
	 * @param request
	 * @param response
	 */
	@Override
	public void onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) {
		
		String host = WebRequestUtils.getRemoteAddr((HttpServletRequest) request );
		subject.getSession().setAttribute(WebContext.SESSION_USER_IP, host);
		
		String czmk = MessageUtil.getTextOnly("log.login");
		String ywmk = MessageUtil.getTextOnly("log.login.cx");
		String czms = MessageUtil.getTextOnly("log.login.ms");
		BizLogUtils.login(token.getPrincipal().toString(), ywmk, czmk, czms);
 		
	}

}
