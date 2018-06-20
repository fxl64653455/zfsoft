/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.event.listener;

import java.util.List;
import java.util.Map;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiAppService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.setup.mq.MessageWs;
import com.zfsoft.boot.apimgr.setup.oauth.ApiOauthWS;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties.AuthType;
import com.zfsoft.boot.flyway.ext.FlywayMigratedEvent;

import javassist.ClassClassPath;
import javassist.ClassPool;

@Component
public class APIDeployOnFlywayMigratedEventListener implements ApplicationListener<FlywayMigratedEvent> {

	@Autowired
	@Qualifier("endpointMap")
	private Map<String, EndpointImpl> endpointMap;
	@Autowired
    private Bus bus;
	@Autowired
	private ApiOauthWS oauthWs;
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private IApiAppService appService;
	@Autowired
	private IApiDeployService deployService;
	@Autowired
	private MessageWs messageWs;
	

	@Override
	public void onApplicationEvent(FlywayMigratedEvent event) {
		
		/**根据认证类型初始化"我的应用"菜单*/
		if(oauth.getAuthType() == AuthType.api) {
			appService.deleteAppMenu();
		}else if(oauth.getAuthType() == AuthType.app) {
			appService.addAppMenu();
		}
		
		if(oauth.isEnabled()) {
			/**发布一个获取认证信息的WebService*/
			EndpointImpl endpoint = new EndpointImpl(bus, oauthWs);
			endpoint.publish("/services/oauth2/accessToken");
			endpointMap.put("apiOauth2WS", endpoint);
		}
		
		/**发布一个发送消息的WebService*/
		EndpointImpl endpoint = new EndpointImpl(bus, messageWs);
		endpoint.publish("/services/rocketMq/sendMessage");
		endpointMap.put("messageWs", endpoint);
		
		/**为defaultPool添加一个类路径 : http://www.codeweblog.com/%E5%85%B3%E4%BA%8Ejavassist-notfoundexception/*/
		ClassClassPath classPath = new ClassClassPath(this.getClass());
		ClassPool.getDefault().insertClassPath(classPath);
		
		List<ApiDeployModel> apiDeployList =  deployService.getModelList();
    	
    	for (ApiDeployModel apiModel : apiDeployList) {
			if(apiModel.getStatus().equals("1")) {
				/**可用状态则发布*/
				deployService.deploy(apiModel);
			}
		}
    	
		
	}
	
}
