package com.zfsoft.boot.ms.provider;

import com.zfsoft.basemodel.BaseMap;
import com.zfsoft.boot.ms.MessageBody;

public interface MessageInputProvider {

	/**
	 * 
	 *@描述		：服务提供者名称
	 *@创建人		: wandalong
	 *@创建时间	: 2017年4月13日下午12:19:06
	 *@return
	 *@修改人		: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public String name();
	
	public BaseMap input(MessageBody data);
	
}
