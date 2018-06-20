/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.webv5.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zfsoft.boot.webv5.setup.property.BootWebv5Properties;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class FrontIndexController {
	
	@Autowired
	private BootWebv5Properties webv5Properties;
	
    /**
     * @description	： 前端首页
     * @author 		： 万大龙（743）
     * @date 		：2017年9月28日 上午9:16:33
     * @param request
     * @return
     */
	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		return "redirect:" + webv5Properties.getFrontIndex();
	}
    
}
