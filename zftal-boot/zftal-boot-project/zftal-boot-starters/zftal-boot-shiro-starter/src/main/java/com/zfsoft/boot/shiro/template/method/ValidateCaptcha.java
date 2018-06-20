/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.template.method;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zfsoft.boot.shiro.ShiroBizProperties;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * @className	： ValidateCaptcha
 * @description	：  提供Freemarker模板判断是否启用验证码功能
 * @author 		：万大龙（743）
 * @date		： 2017年11月8日 上午10:50:49
 * @version 	V1.0
 */
public class ValidateCaptcha implements TemplateMethodModelEx {

	@Autowired
	private ShiroBizProperties properties;
	
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		return new SimpleScalar(Boolean.toString(properties.isValidateCaptcha()));
	}

}
