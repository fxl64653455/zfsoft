/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.cas.realm;


import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.zfsoft.boot.shiro.ShiroCasProperties;
import com.zfsoft.boot.shiro.cas.token.ZFCasToken;
import com.zfsoft.boot.shiro.cas.utils.CasTicketValidatorUtils;
import com.zfsoft.shiro.authc.DefaultAccount;
import com.zfsoft.shiro.authc.DelegateAuthenticationInfo;
import com.zfsoft.shiro.realm.RealmListener;
import com.zfsoft.shiro.realm.SsoAccountRealm;

public class ZFCasInternalRealm extends SsoAccountRealm {

    private static Logger log = LoggerFactory.getLogger(ZFCasInternalRealm.class);
    
    // this class from the CAS client is used to validate a service ticket on CAS server
    private TicketValidator ticketValidator;
    private ShiroCasProperties casProperties;
    
    public ZFCasInternalRealm(ShiroCasProperties casProperties) {
        setAuthenticationTokenClass(ZFCasToken.class);
        setCasProperties(casProperties);
    }

    @Override
    protected void onInit() {
        super.onInit();
        ensureTicketValidator();
    }

    protected TicketValidator ensureTicketValidator() {
        if (this.ticketValidator == null) {
            this.ticketValidator = CasTicketValidatorUtils.createTicketValidator(casProperties);
        }
        return this.ticketValidator;
    }
    
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
    	log.info("Handle authentication token {}.", new Object[] { token });
		
		ZFCasToken casToken = (ZFCasToken) token;
		if (token == null) {
			return null;
		}
		
		AuthenticationException ex = null;
		AuthenticationInfo info = null;
		try {
			
			String ticket = (String) casToken.getCredentials();
			// 如果要获取用户的更多信息，用如下方法：
			Assertion assertion = AssertionHolder.getAssertion();
			if(assertion != null) {

	     		//获取AttributePrincipal对象，这是客户端对象
	     		AttributePrincipal principal = assertion.getPrincipal();
	     		String username = principal.getName();
	     		//获取更多用户属性
	     		Map<String, Object> attributes = principal.getAttributes(); 
	     		
	     		casToken.setUsername(username);
	     		casToken.setAttrs(attributes);
	     		
	            String rememberMeAttributeName = casProperties.getRememberMeAttributeName();
	            String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
	            boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
	            if (isRemembered) {
	                casToken.setRememberMe(true);
	            }
	     		
			} else if (StringUtils.hasText(ticket)) {
			
				try {
					
					TicketValidator ticketValidator = ensureTicketValidator();
					
					// contact CAS server to validate service ticket
					Assertion casAssertion = ticketValidator.validate(ticket, casProperties.getServerName());
					// get principal, user id and attributes
					AttributePrincipal casPrincipal = casAssertion.getPrincipal();
					String username = casPrincipal.getName();
					log.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{
					     ticket, casProperties.getCasServerUrlPrefix(), username
					});

					Map<String, Object> attributes = casPrincipal.getAttributes();
					// refresh authentication token (user id + remember me)
					casToken.setUsername(username);
					casToken.setAttrs(attributes);
					
					String rememberMeAttributeName = casProperties.getRememberMeAttributeName();
					String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
					boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
					if (isRemembered) {
					    casToken.setRememberMe(true);
					}
				} catch (Exception e) {
					throw new AuthenticationException("Unable to validate ticket [" + ticket + "]", e);
				}
			} else if (StringUtils.hasText(casToken.getUsername())) {
				
			}
			
			// do real thing
			// new delegate authentication token and invoke doAuthc method
			DelegateAuthenticationInfo delegateAuthenticationInfo = getAccountService()
					.getAuthenticationInfo(createDelegateAuthenticationToken(casToken));
			if (delegateAuthenticationInfo != null) {
				info = new DefaultAccount(delegateAuthenticationInfo.getPrincipal(),
						delegateAuthenticationInfo.getCredentials(), getName());
			}
		} catch (AuthenticationException e) {
			ex = e;
		}
		
		//调用事件监听器
		if(getRealmsListeners() != null && getRealmsListeners().size() > 0){
			for (RealmListener realmListener : getRealmsListeners()) {
				if(ex != null || null == info){
					realmListener.onAuthenticationFail(casToken, ex);
				}else{
					realmListener.onAuthenticationSuccess(info,SecurityUtils.getSubject().getSession());
				}
			}
		}
		
		if(ex != null){
			throw ex;
		}
		
		return info;
	}
    

    public ShiroCasProperties getCasProperties() {
		return casProperties;
	}

	public void setCasProperties(ShiroCasProperties casProperties) {
		this.casProperties = casProperties;
	}
    
}
