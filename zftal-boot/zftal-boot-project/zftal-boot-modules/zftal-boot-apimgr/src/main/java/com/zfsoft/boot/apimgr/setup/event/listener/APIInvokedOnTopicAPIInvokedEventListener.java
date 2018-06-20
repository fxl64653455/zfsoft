/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.event.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
//import com.zfsoft.boot.apimgr.dao.entities.ApiInvokeModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
//import com.zfsoft.boot.apimgr.service.svcinterface.IApiInvokeService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiMetircService;
import com.zfsoft.boot.apimgr.setup.count.InvokeCount;
import com.zfsoft.boot.apimgr.setup.event.ApiInvokedEvent;
import com.zfsoft.boot.apimgr.web.handler.ApiHandlerExec;
import com.zfsoft.boot.rocketmq.RocketmqProducerTemplate;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

@Component
public class APIInvokedOnTopicAPIInvokedEventListener implements ApplicationListener<ApiInvokedEvent> {

	@Autowired(required = false)
	private RocketmqProducerTemplate rocketmqTemplate;
	@Autowired
	private IApiDeployService apiDeployService;
	@Autowired
	private IApiMetircService metircService;
	@Autowired(required = false)
	private InvokeCount invokeCount;
	@Autowired
	private ApiHandlerExec apiExec;
//	@Autowired
//	private IApiInvokeService invokeService;

	@Async
	@Override
	public void onApplicationEvent(ApiInvokedEvent event) {
		
		if (getRocketmqTemplate() == null) {
			return;
		}
		
		// 接口调用发生异常
		if(event.getEx() != null) {
			return;
		}

		// 1、 查询触发该事件的接口信息
		ApiDeployModel deploy = getApiDeployService().findDeployById(event.getDeployId(), true);
		if(deploy == null) {
			return;
		}
		// 2、触发该接口绑定的消息事件
		JSONObject mq = JSONObject.parseObject(deploy.getDesc()).getJSONObject("bind");
		if (mq != null && mq.containsKey("rocketmq") 
				&& mq.getJSONObject("rocketmq").getString("topic") != null && !"".equals(mq.getJSONObject("rocketmq").getString("topic")) 
				&& mq.getJSONObject("rocketmq").getString("tag") != null && !"".equals(mq.getJSONObject("rocketmq").getString("tag"))) {
			try {

				String topic = mq.getJSONObject("rocketmq").getString("topic");
				String tags = mq.getJSONObject("rocketmq").getString("tag");

				JSONObject text = new JSONObject();
				text.put("type", "dsb");
				text.put("deployId", event.getDeployId());
				text.put("desc", deploy.getDesc());
				text.put("invokePar", event.getParams());
				text.put("res", event.getResult());
				text.put("startTime", event.getStartTime());
				text.put("endTime", System.currentTimeMillis());

				getRocketmqTemplate().send(topic, tags, event.getDeployId(), text.toJSONString());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 2、记录该接口调用记录（本地数据库记录）
		try {
			
			ApiMetircModel model = new ApiMetircModel();
			
			//关联的业务ID、业务名称
			model.setBizId(event.getDeployId());
			model.setBizName(deploy.getApiName());
			
			/*
			  {
			 	  "appkey" 		: "xadasdasdasdasd",
			      "addr"		: "addr",
			      "bizName"		: "bizName",
			  	  "uri"			: "test/xxx",
			  	  "userAgent"	: "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0"
			  }
			 */
			
			model.setAppKey(event.getAppkey());
			model.setUri(event.getRequestURI());
			model.setAddr(event.getRemoteAddr());
			
			//原始userAgent数据
			String userAgentStr = event.getUserAgent();
			model.setUserAgent(userAgentStr);
			
			//解析userAgent
			UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
			//解析操作系统信息
			OperatingSystem os = userAgent.getOperatingSystem();
			if(os!=null) {
				model.setOsName(os.getName());
				model.setOsVer(String.valueOf(os.getId()));
				model.setOsMfr(os.getManufacturer().getName());
				//解析设备信息
				DeviceType deviceType = os.getDeviceType();
				if(deviceType!=null) {
					model.setDeviceName(deviceType.getName());
				}
			}
			//解析浏览器信息
			Browser browser = userAgent.getBrowser();
			if(browser!=null) {
				model.setBrowserName(browser.getName());
				model.setBrowserType(browser.getBrowserType().getName());
				model.setBrowserKernel(browser.getRenderingEngine().name());
			}
			Version ver = userAgent.getBrowserVersion();
			if(ver!=null) {
				model.setBrowserVer(ver.getVersion());
			}
			
			getMetircService().insert(model);
			
			if(invokeCount != null) {
				invokeCount.increase(event.getAppkey(), 1);
			}
			
		} catch (Exception e) {
			
		}

//		/**连续调用(影响所有发布接口)*/
//		ApiInvokeModel aim = invokeService.getModel(deploy.getApi().getId());
//		if(aim != null) {
//			JSONArray pr = JSONArray.parseArray(aim.getParamRelation());
//			Map<String, Object> par = new HashMap<>();
//			for (int i = 0;i < pr.size();i ++) {
//				par.put(pr.getJSONObject(i).getString("name"), event.getParams().get(pr.getJSONObject(i).getString("key")));
//			}
//			try {
//				apiExec.exec(aim.getInvokeDeployId(), par);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		/**连续调用(不响之前发布的版本)*/
		JSONObject invoke = JSONObject.parseObject(deploy.getDesc()).getJSONObject("invoke");
		if(invoke != null) {
			JSONArray pr = invoke.getJSONArray("paramRelation");
			Map<String, Object> par = new HashMap<>();
			for (int i = 0;i < pr.size();i ++) {
				par.put(pr.getJSONObject(i).getString("name"), event.getParams().get(pr.getJSONObject(i).getString("key")));
			}
			try {
				apiExec.exec(invoke.getString("invokeDeployId"), par);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public RocketmqProducerTemplate getRocketmqTemplate() {
		return rocketmqTemplate;
	}

	public void setRocketmqTemplate(RocketmqProducerTemplate rocketmqTemplate) {
		this.rocketmqTemplate = rocketmqTemplate;
	}

	public IApiDeployService getApiDeployService() {
		return apiDeployService;
	}

	public void setApiDeployService(IApiDeployService apiDeployService) {
		this.apiDeployService = apiDeployService;
	}

	public IApiMetircService getMetircService() {
		return metircService;
	}

	public void setMetircService(IApiMetircService metircService) {
		this.metircService = metircService;
	}
	
}
