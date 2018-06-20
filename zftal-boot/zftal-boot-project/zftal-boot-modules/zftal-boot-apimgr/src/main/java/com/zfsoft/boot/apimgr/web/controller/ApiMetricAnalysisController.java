/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiMetircAnalysisService;
import com.zfsoft.boot.apimgr.util.PropertyUtils;
import com.zfsoft.boot.apimgr.web.dto.ApiMetircDto;
import com.zfsoft.webmvc.controller.BaseController;

/**
 * 
 * @className	： ApiMetricAnalysisController
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 下午6:25:57
 * @version 	V1.0
 */
@Controller
@RequestMapping("/manager/api/analysis/")
public class ApiMetricAnalysisController extends BaseController {

	@Autowired
	private IApiMetircAnalysisService metircAnalysisService;

	/**
	 * 
	 * @description ： 接口数据监控分析主界面;支持嵌入业务系统
	 * @author ： 万大龙（743）
	 * @date ：2017年11月28日 上午10:29:43
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/index", produces = "text/html; charset=UTF-8")
	public String index(@RequestParam(name = "inner",required = false) String inner, ModelMap model) {
		
		ApiMetircModel apiModel = new ApiMetircModel();
		model.addAttribute("appList", getMetircAnalysisService().appList(apiModel));
		model.addAttribute("bizList", getMetircAnalysisService().bizList(apiModel));
		
		return "html/api/metric/analysis/index";
	}
	
	@PostMapping("metircs")
	@ResponseBody
	public Object metircs(ApiMetircDto dto) {
		Map<String, Object> metircMap = Maps.newConcurrentMap();
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			// 应用访问占比、访问前5条
			metircMap.put("appRatio", getMetircAnalysisService().appRatio(model));
			metircMap.put("appTop10", getMetircAnalysisService().appTop10(model));
			metircMap.put("appDaily", getMetircAnalysisService().appDaily(model));
			// 业务占比、访问前5条
			metircMap.put("bizRatio", getMetircAnalysisService().bizRatio(model));
			metircMap.put("bizTop10", getMetircAnalysisService().bizTop10(model));
			metircMap.put("bizDaily", getMetircAnalysisService().bizDaily(model));
			// 设备占比、访问前5条
			metircMap.put("deviceRatio", getMetircAnalysisService().deviceRatio(model));
			metircMap.put("deviceTop10", getMetircAnalysisService().deviceTop10(model));
			metircMap.put("deviceDaily", getMetircAnalysisService().deviceDaily(model));
			// 操作系统占比、访问前5条
			metircMap.put("osRatio", getMetircAnalysisService().osRatio(model));
			metircMap.put("osTop10", getMetircAnalysisService().osTop10(model));
			metircMap.put("osDaily", getMetircAnalysisService().osDaily(model));
			// 浏览器占比、访问前5条
			metircMap.put("browserRatio", getMetircAnalysisService().browserRatio(model));
			metircMap.put("browserTop10", getMetircAnalysisService().browserTop10(model));
			metircMap.put("browserDaily", getMetircAnalysisService().browserDaily(model));
			
		} catch (Exception e) {
			logException(this, e);
		}
		return metircMap;
	}
	
	@PostMapping("ratio")
	@ResponseBody
	public Object ratio(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().ratio(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("top5")
	@ResponseBody
	public Object top5(ApiMetircDto dto) {

		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			List<Map<String, String>> top5List = getMetircAnalysisService().top5(model);
			return top5List;
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}

	@PostMapping("top10")
	@ResponseBody
	public Object top10(ApiMetircDto dto) {

		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			List<Map<String, String>> top5List = getMetircAnalysisService().top10(model);
			return top5List;
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}

	@PostMapping("top50")
	@ResponseBody
	public Object top50(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			List<Map<String, String>> top5List = getMetircAnalysisService().top50(model);
			return top5List;
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}

	@PostMapping("top100")
	@ResponseBody
	public Object top100(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			List<Map<String, String>> top5List = getMetircAnalysisService().top100(model);
			return top5List;
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("bizRatio")
	@ResponseBody
	public Object bizRatio(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().bizRatio(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("bizTop5")
	@ResponseBody
	public Object bizTop5(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().bizTop5(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("bizTop10")
	@ResponseBody
	public Object bizTop10(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().bizTop10(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("appRatio")
	@ResponseBody
	public Object appRatio(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().appRatio(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("appTop5")
	@ResponseBody
	public Object appTop5(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().appTop5(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("appTop10")
	@ResponseBody
	public Object appTop10(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().appTop10(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	
	@PostMapping("deviceRatio")
	@ResponseBody
	public Object deviceRatio(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().deviceRatio(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("deviceTop5")
	@ResponseBody
	public Object deviceTop5(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().deviceTop5(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("deviceTop10")
	@ResponseBody
	public Object deviceTop10(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().deviceTop10(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("osRatio")
	@ResponseBody
	public Object osRatio(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().osRatio(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("osTop5")
	@ResponseBody
	public Object osTop5(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().osTop5(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("osTop10")
	@ResponseBody
	public Object osTop10(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().osTop10(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("browserRatio")
	@ResponseBody
	public Object browserRatio(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().browserRatio(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("browserTop5")
	@ResponseBody
	public Object browserTop5(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().browserTop5(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	@PostMapping("browserTop10")
	@ResponseBody
	public Object browserTop10(ApiMetircDto dto) {
		try {
			ApiMetircModel model = new ApiMetircModel();
			PropertyUtils.copyProperties(model, dto);
			return getMetircAnalysisService().browserTop10(model);
		} catch (Exception e) {
			logException(this, e);
			return CollectionUtils.EMPTY_COLLECTION;
		}
	}
	
	public IApiMetircAnalysisService getMetircAnalysisService() {
		return metircAnalysisService;
	}

	public void setMetircAnalysisService(IApiMetircAnalysisService metircAnalysisService) {
		this.metircAnalysisService = metircAnalysisService;
	}

}
