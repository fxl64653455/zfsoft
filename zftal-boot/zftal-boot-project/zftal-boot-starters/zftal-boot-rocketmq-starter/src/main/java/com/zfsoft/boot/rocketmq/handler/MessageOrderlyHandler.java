package com.zfsoft.boot.rocketmq.handler;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.common.message.MessageExt;

/**
 */
public interface MessageOrderlyHandler {
	
	
	boolean preHandle(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception;
	
	/**
	 * 
	 * @description	：  处理消息的接口
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月14日 下午4:34:55
	 * @param msgExt 消息对象
     * @param context 上下文
     * @return 是否处理完成
     * @throws Exception 处理异常
	 */
	void handleMessage(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception;
    
	void postHandle(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception;
    
    void afterCompletion(MessageExt msgExt, ConsumeOrderlyContext context, Exception ex) throws Exception;
    
}