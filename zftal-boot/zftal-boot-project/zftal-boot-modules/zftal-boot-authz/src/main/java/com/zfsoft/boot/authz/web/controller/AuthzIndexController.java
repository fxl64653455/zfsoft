/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.authz.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zfsoft.shiro.SubjectUtils;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
public class AuthzIndexController {
	
	@RequestMapping(value="callback")
    public String callback(HttpServletRequest request, Model model) {
    	boolean authenticated = SubjectUtils.isAuthenticated();
    	/**
    	 * 如果用户已登录，直接转发到首页
    	 */
    	if(authenticated){
    		return "redirect:/index";
    	}
    	return "redirect:/authz/login/slogin";
    }
 
}
