package com.zfsoft.boot.freemarker.template.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * @className	： EnvironmentMethod
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月16日 下午12:53:28
 * @version 	V1.0
 */
@Component("envMethod")
public class EnvironmentMethod implements TemplateMethodModelEx {

	@Autowired
	protected Environment env;
	
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (!CollectionUtils.isEmpty(arguments) && arguments.get(0) != null && StringUtils.hasText(arguments.get(0).toString())) {
			String key = arguments.get(0).toString();
			return new SimpleScalar(env.getProperty(key));
		}
		return null;
	}
	
}
