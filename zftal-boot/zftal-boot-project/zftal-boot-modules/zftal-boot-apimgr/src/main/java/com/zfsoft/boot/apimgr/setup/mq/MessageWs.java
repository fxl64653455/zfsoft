/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.mq;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebParam.Mode;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.boot.rocketmq.RocketmqProducerTemplate;

@Component
@WebService(name = "rocketMq", targetNamespace = "http://dsb.zfsoft.com/")
public class MessageWs {
	
	@Autowired(required = false)
	protected RocketmqProducerTemplate rocketmqTemplate;
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	@WebMethod(action = "http://dsb.zfsoft.com/sendMessage", operationName = "sendMessage")
	public String sendMessage(@WebParam(name = "topic", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String topic,
			@WebParam(name = "tag", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String tag,
			@WebParam(name = "key", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String key,
			@WebParam(name = "content", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String content) {
		
		if(StringUtils.isEmpty(topic)) {
			return ResultUtils.status("fail", "Topic can't be empty.");
		}
		if(StringUtils.isEmpty(tag)) {
			return ResultUtils.status("fail", "Tag can't be empty.");
		}
		if(StringUtils.isEmpty(key)) {
			return ResultUtils.status("fail", "Key can't be empty.");
		}
		if(StringUtils.isEmpty(content)) {
			return ResultUtils.status("fail", "Content can't be empty.");
		}
		
        try {  
        	
        	Message msg = new Message(topic,tag,key,content.getBytes());
    		//发送消息
        	SendResult result = rocketmqTemplate.send(msg);
    		//判断发送状态
    		if(SendStatus.SEND_OK.compareTo(result.getSendStatus()) == 0) {
    			return ResultUtils.status("success", "SEND_OK");
    		}
        } catch (Exception e) {  
            e.printStackTrace();
        }
		
		return ResultUtils.status("fail", messageSource.getMessage("MQ-I000001",null,WebContext.getLocale()));
	}
	
}
