/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.mybatis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfsoft.boot.mybatis.service.svcinterface.IUuidService;

@RestController
public class HelloWorldController {
	
	@Autowired
	protected IUuidService uuidService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String uuid() {
        return "uuid: " + uuidService.get();
    }
    
}
