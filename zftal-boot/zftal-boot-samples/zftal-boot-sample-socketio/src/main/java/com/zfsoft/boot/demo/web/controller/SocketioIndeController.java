/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SocketioIndeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String uuid() {
        return "html/socketio/index";
    }
    
}
