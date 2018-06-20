package com.zfsoft.boot.authz.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.Initializable;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zfsoft.api.security.RSALoginService;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.kaptcha.ext.CaptchaResolver;
import com.zfsoft.shiro.IncorrectCaptchaException;
import com.zfsoft.shiro.InvalidAccountException;
import com.zfsoft.shiro.filter.BaseAuthenticationFilter;
import com.zfsoft.shiro.token.CaptchaAuthenticationToken;
import com.zfsoft.shiro.token.DefaultAuthenticationToken;
import com.zfsoft.web.utils.WebRequestUtils;

/**
 * 
 * @className	： DefaultAuthenticationFilter
 * @description	： 默认的登录认证实现
 * @author 		：万大龙（743）
 * @date		： 2017年10月10日 下午5:14:29
 * @version 	V1.0
 */
public class DefaultAuthenticationFilter extends BaseAuthenticationFilter implements Initializable{

	public static final String DEFAULT_YHM_PARAM = "yhm";
	
	public static final String DEFAULT_MM_PARAM = "mm";
	
	public static final String DEFAULT_YZM_PARAM = "yzm";
	
	private String yzmParam = DEFAULT_YZM_PARAM;
	
	private RSALoginService rsaLoginService = new RSALoginService();
	
	private CaptchaResolver captchaResolver;
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String userName = getUsername(request);
		String pwd = getPassword(request);
		
		if( !StringUtils.hasText(userName) || !StringUtils.hasText(pwd)){
			throw new InvalidAccountException("Username or password is incorrect.");
		}
		
		String password = rsaLoginService.decryptParameter(pwd , (HttpServletRequest)request);
		rsaLoginService.removePrivateKey((HttpServletRequest)request);
		String validateCode = getYzm(request);
		//String host = getHost(request);
		String host = WebRequestUtils.getRemoteAddr((HttpServletRequest) request );
		
		DefaultAuthenticationToken token = new DefaultAuthenticationToken();
		token.setCaptcha(validateCode);
		token.setHost(host);
		token.setPassword(StringUtils.isNull(password) ? null : password.toCharArray());
		token.setUsername(userName);
		
		return token;
	}
	
	@Override
	protected void validateCaptcha(Session session, CaptchaAuthenticationToken token) {
		boolean validation = true;
		if (isValidateCaptcha() && captchaResolver != null){
			validation = captchaResolver.validCaptcha((HttpServletRequest) WebContext.getRequest(), token.getCaptcha());
		} else if (isValidateCaptcha()){
			validation = validateCaptcha((String)session.getAttribute(getSessoionCaptchaKey()), token.getCaptcha());
		}
		if(!validation){
			throw new IncorrectCaptchaException("Captcha validation failed!");
		}
	}
	
	protected String getPassword(ServletRequest request) {
		return request.getParameter(DEFAULT_MM_PARAM);
	}

	protected String getUsername(ServletRequest request) {
		return request.getParameter(DEFAULT_YHM_PARAM);
	}

	protected String getYzm(ServletRequest request) {
		return WebUtils.getCleanParam(request, getYzmParam());
	}

	public String getYzmParam() {
		return yzmParam;
	}

	public void setYzmParam(String yzmParam) {
		this.yzmParam = yzmParam;
	}

	@Override
	public void init() throws ShiroException {
	}

	public CaptchaResolver getCaptchaResolver() {
		return captchaResolver;
	}

	public void setCaptchaResolver(CaptchaResolver captchaResolver) {
		this.captchaResolver = captchaResolver;
	}

	/*@Override
	public void init() throws ShiroException {
		String isOpenKaptcha  = MessageUtil.getText("isOpenKaptcha");
		if(StringUtils.isNull(isOpenKaptcha) || Boolean.valueOf(isOpenKaptcha)){
			setValidateCaptcha(true);
		}else{
			setValidateCaptcha(false);
		}
	}
	
	@Override
	public boolean isValidateCaptcha() {
		
		String isOpenKaptcha  = MessageUtil.getText("isOpenKaptcha");
		if(StringUtils.isNull(isOpenKaptcha) || Boolean.valueOf(isOpenKaptcha)){
			return true;
		}
		return properties.isValidateCaptcha();
	}
	*/
	
	
	
}
