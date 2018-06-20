/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.webmvc.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDozerController extends BaseController {

	@Autowired(required = false)
	private DozerBeanMapper beanMapper;

	public DozerBeanMapper getBeanMapper() {
		return beanMapper;
	}

	public void setBeanMapper(DozerBeanMapper beanMapper) {
		this.beanMapper = beanMapper;
	}
	
}
