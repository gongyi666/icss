package com.lantone.icss.web.kl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;
import com.lantone.icss.api.kl.service.DiagnoseInfoService;

/**
 * @Description:诊断依据
 * @author:ztg
 * @time:2017年4月6日 下午2:43:31
 */
@Controller
@RequestMapping("/kl/diagnose")
public class DiagnoseInfoController {

	private static Logger logger = LoggerFactory.getLogger(DiagnoseInfoController.class);

	@Reference
	DiagnoseInfoService diagnoseInfoService;
	
	/**
	 * @Description:根据疾病id获取诊断依据
	 * @author:ztg
	 * @time:2017年4月6日 下午2:40:16
	 */
	@ResponseBody
	@RequestMapping("/get_by_diseaseid")
	public Response<List<DiagnoseInfoWrapper>> getDiagnosisGuideByDisease(Long diseaseId){
		Response<List<DiagnoseInfoWrapper>> response = new Response<List<DiagnoseInfoWrapper>>();
		response.start();
		try {
			response.setData(diagnoseInfoService.getByDiseaseId(diseaseId));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取诊断依据失败！");
		}
		return response.success();
	}
}
