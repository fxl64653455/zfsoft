/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.webv5.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zfsoft.boot.webv5.setup.property.BootWebv5Properties;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/admin/")
public class AdminIndexController {

	@Autowired
	private BootWebv5Properties webv5Properties;
	
	/**
	 * 
	 * @description	：  管理端首页
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月28日 上午9:17:56
	 * @param request
	 * @return
	 */
	@GetMapping("index")
	public String index(HttpServletRequest request, Model model) {
		return "redirect:" + webv5Properties.getAdminIndex();
	}

}
