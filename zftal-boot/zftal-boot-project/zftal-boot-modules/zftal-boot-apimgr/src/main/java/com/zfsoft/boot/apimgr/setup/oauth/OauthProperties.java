/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.oauth;

public class OauthProperties {
	
	public enum AuthType{
		/**每个应用生成一对密钥 针对应用设置接口访问权限*/
		app,
		/**每个接口生成一对密钥*/
		api
	}
	
	/**是否启用相关功能*/
	private boolean enabled;
	/**认证类型*/
	private AuthType authType;
	/**认证服务器地址*/
	private String server;
	/**appKey*/
	private String clientId;
	/**appSecret*/
	private String clientSecret;
	/**获取授权码地址*/
	private String authorizeUrl;
	/**获取token地址*/
	private String accessTokenUrl;
	
	/**保存客户端信息*/
	private String saveClientUrl;
	/**删除客户端*/
	private String deleteClientUrl;
	/**验证客户端信息*/
	private String checkClientUrl;
	/**根据clientId获取新的Token*/
	private String accessTokenByIdUrl;
	/**验证Token信息*/
	private String checkAccessTokenUrl;
	/**查询客户端信息*/
	private String findByClientIdUrl;
	/**根据ID删除权限组*/
	private String deleteResourceGroupUrl;
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public AuthType getAuthType() {
		return authType;
	}
	public void setAuthType(AuthType authType) {
		this.authType = authType;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getAuthorizeUrl() {
		return authorizeUrl;
	}
	public void setAuthorizeUrl(String authorizeUrl) {
		this.authorizeUrl = authorizeUrl;
	}
	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}
	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}
	/**
	 * @description	： 保存客户端信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月9日 下午3:13:36
	 * @return
	 */
	public String getSaveClientUrl() {
		return saveClientUrl;
	}
	public void setSaveClientUrl(String saveClientUrl) {
		this.saveClientUrl = saveClientUrl;
	}
	/**
	 * @description	： 删除客户端
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月9日 下午3:13:14
	 * @return
	 */
	public String getDeleteClientUrl() {
		return deleteClientUrl;
	}
	public void setDeleteClientUrl(String deleteClientUrl) {
		this.deleteClientUrl = deleteClientUrl;
	}
	/**
	 * @description	： 验证客户端信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月9日 下午3:23:21
	 * @return
	 */
	public String getCheckClientUrl() {
		return checkClientUrl;
	}
	public void setCheckClientUrl(String checkClientUrl) {
		this.checkClientUrl = checkClientUrl;
	}
	/**
	 * @description	： 根据clientId获取新的Token
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月9日 下午3:25:14
	 * @return
	 */
	public String getAccessTokenByIdUrl() {
		return accessTokenByIdUrl;
	}
	public void setAccessTokenByIdUrl(String accessTokenByIdUrl) {
		this.accessTokenByIdUrl = accessTokenByIdUrl;
	}
	/**
	 * @description	： 验证Token信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月9日 下午3:27:40
	 * @return
	 */
	public String getCheckAccessTokenUrl() {
		return checkAccessTokenUrl;
	}
	public void setCheckAccessTokenUrl(String checkAccessTokenUrl) {
		this.checkAccessTokenUrl = checkAccessTokenUrl;
	}
	/**
	 * @description	： 查询客户端信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年12月5日 下午1:46:16
	 * @return
	 */
	public String getFindByClientIdUrl() {
		return findByClientIdUrl;
	}
	public void setFindByClientIdUrl(String findByClientIdUrl) {
		this.findByClientIdUrl = findByClientIdUrl;
	}
	/**
	 * @description	： 根据ID删除权限组
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年12月5日 下午3:50:23
	 * @return
	 */
	public String getDeleteResourceGroupUrl() {
		return deleteResourceGroupUrl;
	}
	public void setDeleteResourceGroupUrl(String deleteResourceGroupUrl) {
		this.deleteResourceGroupUrl = deleteResourceGroupUrl;
	}
}
