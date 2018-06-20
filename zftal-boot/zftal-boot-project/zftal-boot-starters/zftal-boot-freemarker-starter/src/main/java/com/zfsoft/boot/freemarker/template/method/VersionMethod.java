package com.zfsoft.boot.freemarker.template.method;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * @className	： VersionMethod
 * @description	： Freemarker 动态资源尾部版本信息
 * @author 		：万大龙（743）
 * @date		： 2017年9月22日 上午10:49:43
 * @version 	V1.0
 */
@Component("version")
public class VersionMethod implements TemplateMethodModelEx, InitializingBean {

	private String version = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.version = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes());
	}
	
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		return new SimpleScalar(version);
	}
	
}
