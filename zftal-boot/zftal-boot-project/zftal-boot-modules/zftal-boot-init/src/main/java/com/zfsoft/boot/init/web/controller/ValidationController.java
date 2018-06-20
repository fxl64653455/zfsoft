package com.zfsoft.boot.init.web.controller;

import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.boot.init.dao.entities.ValidationModel;
import com.zfsoft.boot.init.service.svcinterface.IValidationService;
import com.zfsoft.boot.init.web.dto.ValidationDto;
import com.zfsoft.webmvc.controller.BaseController;

@Controller
@RequestMapping("/xtgl/validate")
public class ValidationController extends BaseController  {
	
	@Autowired
	private IValidationService service;
	
	/**
	 * 
	 *@描述：查询唯一性验证
	 *@创建人:wandalong
	 *@创建时间:2014-6-17下午08:22:17
	 *@修改人:
	 *@修改时间:
	 *@修改描述:
	 *@return
	 */
	@PostMapping(value = "/cxUnique" )
	@RequiresAuthentication
	@ResponseBody
	public Object cxUnique(@Valid ValidationDto dto){
		try {
			ValidationModel model = new ValidationModel();
			PropertyUtils.copyProperties(model, dto);
			boolean unique = service.unique(model);
			return unique? "1": "0";
		} catch (Exception e) {
			logException(e);
			return errorStatus();
		}
	}

}

