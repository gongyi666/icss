package com.lantone.icss.trans.yiqian.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.his.model.Wrapper.HisDeptInfoWrapper;
import com.lantone.icss.trans.yiqian.service.YiQianDeptService;




/**
 * 
 * @Description:科室信息获取接口
 * @author ynk
 * @date 2017年5月11日 下午14:53:39
 * @version V1.0
 */
@Controller
@RequestMapping("/yiqian/at/dept_info")
public class DeptInfoControlle {
	private static Logger logger = LoggerFactory.getLogger(DeptInfoControlle.class);
	
	@Autowired
	YiQianDeptService service;
	
	@RequestMapping(value = "/get_dept_info")
	@ResponseBody
	public Response<List<HisDeptInfoWrapper>> getDeptInfo(HisDeptInfoWrapper deptInfo) throws Exception {
		Response<List<HisDeptInfoWrapper>> response = new Response<List<HisDeptInfoWrapper>>();
		response.start();
		try {
			logger.info("------------科室信息接口------------");
			List<HisDeptInfoWrapper> hisDeptInfoWrapper = service.remoteDeptQuery(deptInfo.getHospitalCode());
			logger.info("------------科室数据返回------------");
			response.setData(hisDeptInfoWrapper);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}
		

}
