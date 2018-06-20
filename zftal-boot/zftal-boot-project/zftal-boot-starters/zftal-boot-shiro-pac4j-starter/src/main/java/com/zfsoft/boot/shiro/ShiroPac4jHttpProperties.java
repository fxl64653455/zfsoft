/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.pac4j.core.context.Pac4jConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroPac4jHttpProperties.PREFIX)
public class ShiroPac4jHttpProperties {

	public static final String PREFIX = "shiro.pac4j.http";
	
	/** Whether Enable Pac4j Http. */
	private boolean enabled = false;
	/** 登录地址：会话不存在时访问的地址 */
	private String loginUrl;
	
    private String usernameParameter = Pac4jConstants.USERNAME;
    private String passwordParameter = Pac4jConstants.PASSWORD;
	
    /** DirectCasProxyClient */
    
    private boolean formClient = false;
    private String formClientName = "form";
    
    /** IndirectBasicAuthClient */
    
    private boolean indirectBasicAuthClient = false;
    private String indirectBasicAuthClientName = "indirect-basic-auth";
    private String realmName;
    
    /** DirectBasicAuthClient */
    
    private boolean directBasicAuthClient = false;
    private String directBasicAuthClientName = "direct-basic-auth";

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getUsernameParameter() {
		return usernameParameter;
	}

	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

	public String getPasswordParameter() {
		return passwordParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

	public boolean isFormClient() {
		return formClient;
	}

	public void setFormClient(boolean formClient) {
		this.formClient = formClient;
	}

	public String getFormClientName() {
		return formClientName;
	}

	public void setFormClientName(String formClientName) {
		this.formClientName = formClientName;
	}

	public boolean isIndirectBasicAuthClient() {
		return indirectBasicAuthClient;
	}

	public void setIndirectBasicAuthClient(boolean indirectBasicAuthClient) {
		this.indirectBasicAuthClient = indirectBasicAuthClient;
	}

	public String getIndirectBasicAuthClientName() {
		return indirectBasicAuthClientName;
	}

	public void setIndirectBasicAuthClientName(String indirectBasicAuthClientName) {
		this.indirectBasicAuthClientName = indirectBasicAuthClientName;
	}
	
	public String getRealmName() {
		return realmName;
	}

	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}

	public boolean isDirectBasicAuthClient() {
		return directBasicAuthClient;
	}

	public void setDirectBasicAuthClient(boolean directBasicAuthClient) {
		this.directBasicAuthClient = directBasicAuthClient;
	}

	public String getDirectBasicAuthClientName() {
		return directBasicAuthClientName;
	}

	public void setDirectBasicAuthClientName(String directBasicAuthClientName) {
		this.directBasicAuthClientName = directBasicAuthClientName;
	}

}
