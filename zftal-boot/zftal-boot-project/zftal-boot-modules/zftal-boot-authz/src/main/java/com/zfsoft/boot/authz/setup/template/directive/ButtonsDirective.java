package com.zfsoft.boot.authz.setup.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zfsoft.api.web.session.User;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.authz.dao.entities.AncdModel;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzRoleService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：Freemarker工具--加载功能按钮
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a>
 * @version 2016年9月2日下午4:43:02
 */
@Component("buttonsDirective")
public class ButtonsDirective implements TemplateDirectiveModel {

	@Autowired
	private IAuthzRoleService roleService;
	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		
		TemplateModel paramValue = (TemplateModel) params.get("gnmkdm");
		String gnmkdm = ((SimpleScalar)paramValue).getAsString();
		if(StringUtils.hasText(gnmkdm)){
			User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			List<AncdModel> ancdList = roleService.getButtonList(user.getJsdm(), user.getYhm(), gnmkdm);
			
			TemplateModel template = new BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build().wrap(ancdList);
			env.setVariable("ancdList",template);
		} else{
			TemplateModel template = new BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build().wrap(null);
			env.setVariable("ancdList",template);
		}
		
		if (body != null) {  
            body.render(env.getOut());  
        } 
	}

}
