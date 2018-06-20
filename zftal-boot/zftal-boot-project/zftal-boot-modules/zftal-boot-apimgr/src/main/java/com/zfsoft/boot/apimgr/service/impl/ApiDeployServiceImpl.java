/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.ServerImpl;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.enhanced.factory.support.DefaultDynamicControllerRegistry;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiDeployDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiInfoDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.setup.builder.APICtClassBuilder;
import com.zfsoft.boot.apimgr.util.enums.DeployTypeEnum;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;

import javassist.CtClass;

@Service
public class ApiDeployServiceImpl extends BaseServiceImpl<ApiDeployModel, IApiDeployDao> implements IApiDeployService {

	@Autowired
	@Qualifier("endpointMap")
	private Map<String, EndpointImpl> endpointMap;
	
	@Autowired
	@Qualifier("classStore")
	private Map<String, Class<?>> classStore;
	
	@Autowired
    private Bus bus;
	
	@Autowired
    private DefaultDynamicControllerRegistry beanDefinitionRegistry;
	
	@Autowired
	private IApiInfoDao apiInfoDao;
	
	@Autowired
	private ApiOutputHandler apiOutputHandler;
	
	@Override
	public boolean newDeploy(ApiDeployModel deployModel) {
		getDao().insert(deployModel);
		return this.deploy(deployModel);
	}
	
	@Override
	public boolean deploy(ApiDeployModel model) {
		
		try {
			
			// 使用发布地址的md5Hex值作为bean名称
			String beanName = DigestUtils.md5Hex(model.getAddr().getBytes());
			
			if(DeployTypeEnum.CXF.getKey().equalsIgnoreCase(model.getType())) {
				
				Class<?> apiClass = classStore.get(beanName);
				if(apiClass == null) {
					CtClass c = new APICtClassBuilder(apiOutputHandler, model).wsApi().build();
					apiClass = c.toClass();
					/**在classPool中删除*/
					c.detach();
					/**存在classStore中*/
					classStore.put(beanName, apiClass);
				}
				
				/**注册对象到spring上下文*/
				try {
					beanDefinitionRegistry.getBean(beanName);
				} catch (NoSuchBeanDefinitionException e) {
					beanDefinitionRegistry.registerBean(beanName, apiClass);
				}
				
				/**发布成ws*/
				EndpointImpl endpoint = new EndpointImpl(bus, beanDefinitionRegistry.getBean(beanName));
				endpoint.publish(model.getAddr());
				// 全局缓存ws引用
				endpointMap.put(beanName, endpoint);
				
			} else if(DeployTypeEnum.REST.getKey().equalsIgnoreCase(model.getType())) {
				
				// 创建接口Class
				Class<?> apiClass = classStore.get(beanName);
				if(apiClass == null) {
					CtClass c = new APICtClassBuilder(apiOutputHandler, model).restApi().build();
					apiClass = c.toClass();
					/**在classPool中删除*/
					c.detach();
					/**暂存在classStore中以便可以重新注册到spring上下文中*/
					classStore.put(beanName, apiClass);
				}
				
				// 注册对象到上下文
				GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
			    beanDefinition.setBeanClass(apiClass);
			    beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
			    beanDefinition.setLazyInit(false);
			    beanDefinition.setAutowireCandidate(true);
			    
				// 注册接口信息到Spring上下文
			    beanDefinitionRegistry.registerController(beanName, beanDefinition);
				
			}
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean redeploy(ApiDeployModel model) {
		boolean isOk = this.undeploy(model);
		if(isOk) {
			isOk = this.deploy(model);
		}
		return isOk;
	}

	@Override
	public boolean undeploy(ApiDeployModel model) {
		try {
			
			if(DeployTypeEnum.CXF.getKey().equalsIgnoreCase(model.getType())) {
				
				// 使用发布地址的md5Hex值作为bean名称
				String beanName = DigestUtils.md5Hex(model.getAddr().getBytes());
				
				EndpointImpl endpoint = endpointMap.get(beanName);
				if(endpoint != null) {
					// 销毁指定的Ws
					ServerImpl server = endpoint.getServer(model.getAddr());
					server.destroy();
					
					endpointMap.remove(beanName);
					
				}
				
			} else if(DeployTypeEnum.REST.getKey().equalsIgnoreCase(model.getType())) {
				
				// 使用发布地址的md5Hex值作为bean名称
				String beanName = DigestUtils.md5Hex(model.getAddr().getBytes());
				
				beanDefinitionRegistry.removeController(beanName);
				
				classStore.remove(beanName);
				
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ApiDeployModel getVersions(String id) {
		return dao.getVersions(id);
	}
	
	@Override
	public int checkAddr(String addr) {
		return dao.checkAddr(addr);
	}

	@Override
	public QueryModel getPagedSearchList(ApiDeployModel apiInfoModel) {
		List<ApiDeployModel> items = dao.getPagedSearchList(apiInfoModel);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (ApiDeployModel apiInfo : items) {
			mapList.add(apiInfo.toMap());
		}
		QueryModel queryModel = apiInfoModel.getQueryModel();
		queryModel.setItems(mapList);
		return queryModel;
	}

	@Override
	public ApiDeployModel findDeployById(String deployId, boolean flag) {
		ApiDeployModel deploy = getModel(deployId);
		if(flag && deploy != null) {
			ApiInfoModel apiInfo = apiInfoDao.getModel(deploy.getApiId());
			deploy.setApiName(apiInfo.getName());
			deploy.setApiInfo(apiInfo.getInfo());
			deploy.setApi(apiInfo);
		}
		return deploy;
	}

	@Override
	public void online(ApiDeployModel model) {
		
		if(this.deploy(model)) {
			model.setStatus("1");
			getDao().update(model);
		}
		
	}

	@Override
	public void offline(ApiDeployModel model) {
		
		if(this.undeploy(model)) {
			model.setStatus("0");
			getDao().update(model);
		}
		
	}
	
	/*@Override
	public boolean delete(ApiDeployModel model) {
		
		if(this.undeploy(model)) {
			getDao().delete(model);
		}
		
		return true;
	}*/

	@Override
	public byte[] getIcon(String deployId) {
		ApiDeployModel model = getDao().getIcon(deployId);
		if(model == null) {
			return null;
		}
		return model.getIconbyte();
	}
	
	public Map<String, EndpointImpl> getEndpointMap() {
		return endpointMap;
	}

	public void setEndpointMap(Map<String, EndpointImpl> endpointMap) {
		this.endpointMap = endpointMap;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public DefaultDynamicControllerRegistry getBeanDefinitionRegistry() {
		return beanDefinitionRegistry;
	}

	public void setBeanDefinitionRegistry(DefaultDynamicControllerRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
	}

	public IApiInfoDao getApiInfoDao() {
		return apiInfoDao;
	}

	public void setApiInfoDao(IApiInfoDao apiInfoDao) {
		this.apiInfoDao = apiInfoDao;
	}

	public ApiOutputHandler getApiOutputHandler() {
		return apiOutputHandler;
	}

	public void setApiOutputHandler(ApiOutputHandler apiOutputHandler) {
		this.apiOutputHandler = apiOutputHandler;
	}

	@Override
	public List<ApiDeployModel> getComboList(ApiDeployModel deployModel) {
		return getDao().getComboList(deployModel);
	}

}
