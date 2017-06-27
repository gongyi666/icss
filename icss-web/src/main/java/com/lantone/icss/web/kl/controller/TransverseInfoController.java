package com.lantone.icss.web.kl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDiseaseWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithLisWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithPacsWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWrapper;
import com.lantone.icss.api.kl.service.TransverseInfoService;

@Controller
@RequestMapping("/kl/transverse")
public class TransverseInfoController {

	private static Logger logger = LoggerFactory.getLogger(TransverseInfoController.class);
	
	@Reference
	TransverseInfoService transverseInfoService;
	
	/**
	 * @Description:获取主诉、现病史、既往史、其他史、体征的横向和纵向数据
	 * @author:ztg
	 * @time:2017年4月18日 下午6:36:19
	 */
	@ResponseBody
	@RequestMapping("/get_transverse_by_type")
	public Response<List<TransverseInfoWrapper>> getTransverseByType(SubitemInfoWrapper param){//type 主诉，既往史，家族史，治疗等
		Response<List<TransverseInfoWrapper>> response = new Response<List<TransverseInfoWrapper>>();
		response.start();
		try {
			List<TransverseInfoWrapper> data = transverseInfoService.getTransverseByType(param);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:获取药横向、纵向数据
	 * @author:ztg
	 * @time:2017年4月18日 下午6:36:19
	 */
	@ResponseBody
	@RequestMapping("/get_transverse_portrait_drug")
	public Response<List<TransverseInfoWithDrugWrapper>> getTransverseWithDrug(String type) {
		Response<List<TransverseInfoWithDrugWrapper>> response = new Response<List<TransverseInfoWithDrugWrapper>>();
		response.start();
		try {
			List<TransverseInfoWithDrugWrapper> data = transverseInfoService.getTransverseWithDrug(type);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询失败");
		}
		return response.success();
		
	}
	
	
	/**
	 * @Description:获取化验横向、纵向数据
	 * @author:ztg
	 * @time:2017年4月18日 下午6:36:19
	 */
	@ResponseBody
	@RequestMapping("/get_transverse_portrait_lis")
	public Response<List<TransverseInfoWithLisWrapper>> getTransverseWithLis(String type) {
		Response<List<TransverseInfoWithLisWrapper>> response = new Response<List<TransverseInfoWithLisWrapper>>();
		response.start();
		try {
			List<TransverseInfoWithLisWrapper> data = transverseInfoService.getTransverseWithLis(type);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询化验横向和纵向数据失败");
		}
		return response.success();
		
	}
	
	
	/**
	 * @Description:获取器查横向、纵向数据
	 * @author:ztg
	 * @time:2017年4月18日 下午6:36:19
	 */
	@ResponseBody
	@RequestMapping("/get_transverse_portrait_pacs")
	public Response<List<TransverseInfoWithPacsWrapper>> getTransverseWithPacs(String type) {
		Response<List<TransverseInfoWithPacsWrapper>> response = new Response<List<TransverseInfoWithPacsWrapper>>();
		response.start();
		try {
			List<TransverseInfoWithPacsWrapper> data = transverseInfoService.getTransverseWithPacs(type);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询器查横向和纵向数据失败");
		}
		return response.success();
		
	}
	
	/**
	 * @Description:获取诊断横向、纵向数据
	 * @author:ztg
	 * @time:2017年4月18日 下午6:36:19
	 */
	@ResponseBody
	@RequestMapping("/get_transverse_portrait_disease")
	public Response<List<TransverseInfoWithDiseaseWrapper>> getTransverseWithDisease(String type) {
		Response<List<TransverseInfoWithDiseaseWrapper>> response = new Response<List<TransverseInfoWithDiseaseWrapper>>();
		response.start();
		try {
			List<TransverseInfoWithDiseaseWrapper> data = transverseInfoService.getTransverseWithDisease(type);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询诊断横向和纵向数据失败");
		}
		return response.success();
		
	}
}
