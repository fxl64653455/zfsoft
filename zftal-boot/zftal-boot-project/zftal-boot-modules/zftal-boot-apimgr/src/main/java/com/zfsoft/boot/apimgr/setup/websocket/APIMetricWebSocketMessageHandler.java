/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.websocket;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiMetircAnalysisService;
import com.zfsoft.boot.websocket.annotation.SocketConsumer;
import com.zfsoft.boot.websocket.event.WebSocketMessageEvent;
import com.zfsoft.boot.websocket.session.handler.WebSocketMessageHandler;
import com.zfsoft.boot.websocket.session.handler.chain.HandlerChain;
import com.zfsoft.boot.websocket.utils.TextMessageBuilder;

/**
 * WebSocket被动数据推送: 该方式优点是接受客户端参数根据参数推送数据，缺点是无法主动推送数据
 */
@Component("apiMetricWebSocketHandler")
@SocketConsumer(path = "/apiMetrics/**")
public class APIMetricWebSocketMessageHandler implements WebSocketMessageHandler<WebSocketMessageEvent> {

	@Autowired
	private IApiMetircAnalysisService metircAnalysisService;
	
	@Override
	public void doHandler(WebSocketMessageEvent event, HandlerChain<WebSocketMessageEvent> handlerChain)
			throws Exception {
		if (event.getMessage() instanceof TextMessage) {
			TextMessage message = (TextMessage) event.getMessage();
			JSONObject object = JSONObject.parseObject(message.getPayload());
			
			ApiMetircModel model = new ApiMetircModel();

			model.setAppKey(object.getString("appKey"));
			model.setBizName(object.getString("bizName"));
			
			Map<String, Object> metircMap = Maps.newConcurrentMap();

			// 应用访问占比、访问前5条
			metircMap.put("appRatio", getMetircAnalysisService().appRatio(model));
			metircMap.put("appTop10", getMetircAnalysisService().appTop10(model));
			metircMap.put("appDaily", getMetircAnalysisService().appDaily(model));
			// 业务占比、访问前5条
			metircMap.put("bizRatio", getMetircAnalysisService().bizRatio(model));
			metircMap.put("bizTop10", getMetircAnalysisService().bizTop10(model));
			metircMap.put("bizDaily", getMetircAnalysisService().bizDaily(model));
			// 设备占比、访问前5条
			metircMap.put("deviceRatio", getMetircAnalysisService().deviceRatio(model));
			metircMap.put("deviceTop10", getMetircAnalysisService().deviceTop10(model));
			metircMap.put("deviceDaily", getMetircAnalysisService().deviceDaily(model));
			// 操作系统占比、访问前5条
			metircMap.put("osRatio", getMetircAnalysisService().osRatio(model));
			metircMap.put("osTop10", getMetircAnalysisService().osTop10(model));
			metircMap.put("osDaily", getMetircAnalysisService().osDaily(model));
			// 浏览器占比、访问前5条
			metircMap.put("browserRatio", getMetircAnalysisService().browserRatio(model));
			metircMap.put("browserTop10", getMetircAnalysisService().browserTop10(model));
			metircMap.put("browserDaily", getMetircAnalysisService().browserDaily(model));

			event.getSession().sendMessage(TextMessageBuilder.get().content(metircMap).build());
			
		}
	}
	
	public IApiMetircAnalysisService getMetircAnalysisService() {
		return metircAnalysisService;
	}

	public void setMetircAnalysisService(IApiMetircAnalysisService metircAnalysisService) {
		this.metircAnalysisService = metircAnalysisService;
	}

}
