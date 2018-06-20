/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.demo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sockets/")
public class WebsocketController {

	@RequestMapping("test")
	public String index(HttpServletRequest request, Model model) {

		return "/html/sockets/test"; 
	}
	
	@RequestMapping("test2")
	public String index2(HttpServletRequest request, Model model) {

		return "/html/sockets/test2"; 
	}

	@RequestMapping("to/{path}")
	public String index3(@PathVariable("path") String path,HttpServletRequest request, Model model) {

		return "/html/sockets/" + path; 
	}

}
