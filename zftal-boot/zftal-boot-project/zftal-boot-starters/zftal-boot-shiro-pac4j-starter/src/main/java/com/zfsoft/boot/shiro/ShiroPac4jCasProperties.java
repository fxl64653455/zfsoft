/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.Pac4jConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.zfsoft.boot.shiro.pac4j.cas.CasClientProperties;

@ConfigurationProperties(ShiroPac4jCasProperties.PREFIX)
public class ShiroPac4jCasProperties extends CasClientProperties {

	public static final String PREFIX = "shiro.pac4j.cas";

	/* ================================== Shiro Pac4j Cas ================================= */
	
	/**
	 * Enable Shiro Pac4j Cas.
	 */
	private boolean enabled = false;
    
	/** The protocol of the CAS Client. */
	private CasProtocol casProtocol = CasProtocol.CAS20_PROXY;
   
    /** CasClient */
    private boolean casClient = false;
    private String casClientName = "CasClient";
    
    /** DirectCasClient */
    
    private boolean directCasClient = false;
    private String directCasClientName = "DirectCasClient";
    
    /** DirectCasProxyClient */
    
    private boolean directCasProxyClient = false;
    private String directCasProxyClientName = "DirectCasProxyClient";
    
    /** CasRestBasicAuthClient */
    
    private boolean casRestBasicAuthClient = false;
    private String casRestBasicAuthClientName = "RestBasicAuthClient";
    private String headerName = HttpConstants.AUTHORIZATION_HEADER;
    private String prefixHeader = HttpConstants.BASIC_HEADER_PREFIX;
    
    /** CasRestFormClient */
    
    private boolean casRestFormClient = false;
    private String casRestFormClientName = "RestFormClient";
    private String usernameParameterName = Pac4jConstants.USERNAME;
    private String passwordParameterName = Pac4jConstants.PASSWORD;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public CasProtocol getCasProtocol() {
		return casProtocol;
	}

	public void setCasProtocol(CasProtocol casProtocol) {
		this.casProtocol = casProtocol;
	}

	public boolean isCasClient() {
		return casClient;
	}

	public void setCasClient(boolean casClient) {
		this.casClient = casClient;
	}

	public String getCasClientName() {
		return casClientName;
	}

	public void setCasClientName(String casClientName) {
		this.casClientName = casClientName;
	}

	public boolean isDirectCasClient() {
		return directCasClient;
	}

	public void setDirectCasClient(boolean directCasClient) {
		this.directCasClient = directCasClient;
	}

	public String getDirectCasClientName() {
		return directCasClientName;
	}

	public void setDirectCasClientName(String directCasClientName) {
		this.directCasClientName = directCasClientName;
	}

	public boolean isDirectCasProxyClient() {
		return directCasProxyClient;
	}

	public void setDirectCasProxyClient(boolean directCasProxyClient) {
		this.directCasProxyClient = directCasProxyClient;
	}

	public String getDirectCasProxyClientName() {
		return directCasProxyClientName;
	}

	public void setDirectCasProxyClientName(String directCasProxyClientName) {
		this.directCasProxyClientName = directCasProxyClientName;
	}

	public boolean isCasRestBasicAuthClient() {
		return casRestBasicAuthClient;
	}

	public void setCasRestBasicAuthClient(boolean casRestBasicAuthClient) {
		this.casRestBasicAuthClient = casRestBasicAuthClient;
	}

	public String getCasRestBasicAuthClientName() {
		return casRestBasicAuthClientName;
	}

	public void setCasRestBasicAuthClientName(String casRestBasicAuthClientName) {
		this.casRestBasicAuthClientName = casRestBasicAuthClientName;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getPrefixHeader() {
		return prefixHeader;
	}

	public void setPrefixHeader(String prefixHeader) {
		this.prefixHeader = prefixHeader;
	}

	public boolean isCasRestFormClient() {
		return casRestFormClient;
	}

	public void setCasRestFormClient(boolean casRestFormClient) {
		this.casRestFormClient = casRestFormClient;
	}

	public String getCasRestFormClientName() {
		return casRestFormClientName;
	}

	public void setCasRestFormClientName(String casRestFormClientName) {
		this.casRestFormClientName = casRestFormClientName;
	}

	public String getUsernameParameterName() {
		return usernameParameterName;
	}

	public void setUsernameParameterName(String usernameParameterName) {
		this.usernameParameterName = usernameParameterName;
	}

	public String getPasswordParameterName() {
		return passwordParameterName;
	}

	public void setPasswordParameterName(String passwordParameterName) {
		this.passwordParameterName = passwordParameterName;
	}

}