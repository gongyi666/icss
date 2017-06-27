package com.lantone.icss.trans.yiqian_new.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.InquiryResult;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.trans.yiqian_new.model.brxx2005.response.VisitedObject;
import com.lantone.icss.trans.yiqian_new.service.NewInquiryService;

/***Title: 
*	Description: 疾病电子病历检验检查写回
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@Controller
@RequestMapping("/yiqian_new/at")
public class NewInquiryController {
	
	private static Logger logger = LoggerFactory.getLogger(NewInquiryController.class);
	
	@Autowired
	NewInquiryService inquiryService;

	
	/**
	 * @Description:保存诊断信息
	 * @author:ztg
	 * @time:2017年5月12日 下午12:04:50
	 */
	@RequestMapping(value = "/saveInquiry")
	@ResponseBody	
	public Response<InquiryResult> saveInquiry(@RequestBody InquiryInfoWrapper info) throws Exception {
		Response<InquiryResult> response = new Response<InquiryResult>();
		response.start();
		try {
			// 调接口
			logger.info("------------诊疗信息保存接口------------");
			response.setData(inquiryService.saveInquiryInfo(info));
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据写入出错!", loadException);
			return response.failure("数据写入出错!");
		}
		return response.success();
	}
	

	/**
	 * @Description:查询
	 * @author:ztg
	 * @time:2017年5月12日 下午12:06:34
	 */
	@RequestMapping(value = "/searchInquiry")
	@ResponseBody	
	public Response<VisitedObject> searchInquiry(Long visitedId) throws Exception {
		Response<VisitedObject> response = new Response<VisitedObject>();
		response.start();
		try {
			// 调接口
			logger.info("------------诊疗信息保存接口------------");
			response.setData(inquiryService.searchInquiry(visitedId));
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据写入出错!", loadException);
			return response.failure("数据写入出错!");
		}
		return response.success();
	}
}