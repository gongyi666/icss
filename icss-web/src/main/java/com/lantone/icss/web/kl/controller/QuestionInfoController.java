package com.lantone.icss.web.kl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.QuestionInfoListWrapper;
import com.lantone.icss.api.kl.model.wrapper.QuestionInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.service.LisInfoService;
import com.lantone.icss.api.kl.service.QuestionInfoService;
import com.lantone.icss.api.kl.service.SubitemInfoService;

@Controller
@RequestMapping("/kl/questioninfo")
public class QuestionInfoController {

	private static Logger logger = LoggerFactory.getLogger(QuestionInfoController.class);
	
	@Reference
	QuestionInfoService questionInfoService;
	@Reference
	LisInfoService lisInfoService;
	@Reference
	SubitemInfoService subitemInfoService;
	
	/**
	 * @Description:根据症状id获取症状要素
	 * @author:luwg
	 * @time:2016年12月15日 上午11:50:40
	 */
	@ResponseBody
	@RequestMapping("/get_question_info")
	public Response<List<QuestionInfoWrapper>> getQuestionInfo(Long symptomId){
		Response<List<QuestionInfoWrapper>> response = new Response<List<QuestionInfoWrapper>>();
		response.start();
		try {
			List<QuestionInfoWrapper> data = questionInfoService.getSymptomQuestion(symptomId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取症状要素失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:获取lis问题(暂时未用)
	 * @author:luwg
	 * @time:2017年1月18日 下午1:25:15
	 */
	@ResponseBody
	@RequestMapping("/get_questioninfo_by_lisid")
	public Response<List<QuestionInfoWrapper>> getQuestionInfoByLisId(Long lisId){
		Response<List<QuestionInfoWrapper>> response = new Response<List<QuestionInfoWrapper>>();
		response.start();
		try {
			List<QuestionInfoWrapper> data = questionInfoService.getLisQuestion(lisId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取lis问题失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:获取pacs问题(暂时未用)
	 * @author:luwg
	 * @time:2017年1月18日 下午1:26:09
	 */
	@ResponseBody
	@RequestMapping("/get_questioninfo_by_pacsid")
	public Response<List<QuestionInfoWrapper>> getQuestionInfoByPacsId(Long pacsId){
		Response<List<QuestionInfoWrapper>> response = new Response<List<QuestionInfoWrapper>>();
		response.start();
		try {
			List<QuestionInfoWrapper> data = questionInfoService.getPacsQuestion(pacsId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取pacs问题失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:获取lisType问题
	 * @author:luwg
	 * @time:2017年1月20日 下午2:52:09
	 */
	@ResponseBody
	@RequestMapping("/get_questioninfo_by_listypeid")
	public Response<Map<String,Object>> getQuestionInfoByLisTypeId(Long lisTypeId){
		Response<Map<String,Object>> response = new Response<Map<String,Object>>();
		response.start();
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			List<QuestionInfoWrapper> question = questionInfoService.getLisTypeQuestion(lisTypeId);
			List<LisInfoWrapper> lis = lisInfoService.getLisByTypeId(lisTypeId);
			data.put("lisInfo", lis);
			data.put("questionInfo", question);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取lisType问题失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:获取体格检查问题
	 * @author:luwg
	 * @time:2017年1月24日 上午9:50:09
	 */
	@ResponseBody
	@RequestMapping("/get_questioninfo_by_physicalid")
	public Response<List<QuestionInfoWrapper>> getQuestionInfoByPhysicalId(Long physicalId){
		Response<List<QuestionInfoWrapper>> response = new Response<List<QuestionInfoWrapper>>();
		response.start();
		try {
			List<QuestionInfoWrapper> data = questionInfoService.getPhysicalQuestion(physicalId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取体格检查问题失败！");
		}
		return response.success();
	}
	
	/**
	 * @Description:获取子项问题
	 * @author:luwg
	 * @time:2017年2月24日 下午4:30:15
	 */
	@ResponseBody
	@RequestMapping("/get_questioninfo_by_subitemid")
	public Response<List<QuestionInfoWrapper>> getQuestionInfoBySubitemId(Long subitemId){
		Response<List<QuestionInfoWrapper>> response = new Response<List<QuestionInfoWrapper>>();
		response.start();
		try {
			List<QuestionInfoWrapper> data = questionInfoService.getSubitemQuestion(subitemId);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:获取子项问题(多个id)
	 * @author:ztg
	 * @time:2017年5月5日 下午3:10:48
	 */
	@ResponseBody
	@RequestMapping("/get_questioninfo_by_subitemids")
	public Response<List<QuestionInfoListWrapper>> getQuestionInfoBySubitemIds(String subitemIds){
		Response<List<QuestionInfoListWrapper>> response = new Response<List<QuestionInfoListWrapper>>();
		response.start();
		try {
			String[] subitem =  subitemIds.split(",");
			List<QuestionInfoListWrapper> resultData = new ArrayList<QuestionInfoListWrapper>();
			for(String  id : subitem) {
				List<QuestionInfoWrapper> ques = questionInfoService.getSubitemQuestion(Long.parseLong(id));
				SubitemInfoWrapper subitemInfoWrapper=subitemInfoService.getSubitemInfoById(Long.parseLong(id));
				if(ques != null && ques.size() > 0){
					QuestionInfoListWrapper bean = new QuestionInfoListWrapper();
					bean.setQuestionList(ques);
					bean.setType(ques.get(0).getType());
					bean.setId(id);
					bean.setName(subitemInfoWrapper.getName());
					bean.setTransCode(subitemInfoWrapper.getTransCode());
					bean.setParamCode(subitemInfoWrapper.getParamCode());
					bean.setStandardId(subitemInfoWrapper.getStandardId());
					resultData.add(bean);
				}
			}
			response.setData(resultData);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
}
