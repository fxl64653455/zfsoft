/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.api.spring.aop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.zfsoft.api.annotation.BusinessLog;
import com.zfsoft.api.utils.BizLogUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.api.web.session.User;

import freemarker.template.TemplateException;

public class BusinessLog4j2Aspect {
	
	/**
	 * 代理对象正常调用返回后advice
	 * @param jp
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public void afterReturing(JoinPoint jp, BusinessLog businessLog) throws TemplateException, IOException{
		
		Signature signature = jp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		
		String[] argNames = methodSignature.getParameterNames();
		Object[] args = jp.getArgs();
		Map<String,Object> data = new HashMap<String,Object>();
		
		for (int i = 0 , j = argNames.length ; i < j ; i++){
			data.put(argNames[i], args[i]);
		}
		
		User user = WebContext.getUser();
		if(user != null){
			//记录业务日志
			BizLogUtils.log(user, businessLog, data);
		}
		
	}

}
