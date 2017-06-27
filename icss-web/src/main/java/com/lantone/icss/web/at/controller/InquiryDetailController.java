package com.lantone.icss.web.at.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.at.service.InquiryDetailService;


/**
 * @Description:问诊记录明细表
 * @author:ztg
 * @time:2017年3月30日 下午1:31:34
 */
@Controller
@RequestMapping("/at/inquiry_detail")
public class InquiryDetailController {

	private static Logger logger = LoggerFactory.getLogger(InquiryDetailController.class);
	
	@Reference 
	InquiryDetailService inquiryDetailService;
	

	/**
	 * @Description:根据id查询明细
	 * @author:ztg
	 * @time:2017年3月30日 上午11:39:36
	 */
	@RequestMapping("/find_by_id")
	@ResponseBody
	public Response<List<InquiryDetailWrapper>>  index(InquiryDetailWrapper detail) {
		Response<List<InquiryDetailWrapper>> response = new Response<List<InquiryDetailWrapper>>();
		response.start();
		try {
			response.setData(inquiryDetailService.findByInquiryId(detail));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询问诊记录明细失败！");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:根据id查询明细
	 * @author:ztg
	 * @time:2017年3月30日 上午11:39:36
	 */
	@RequestMapping("/get_latest")
	@ResponseBody
	public Response<List<InquiryDetailWrapper>>  getLatest(InquiryInfoWrapper info) {
		Response<List<InquiryDetailWrapper>> response = new Response<List<InquiryDetailWrapper>>();
		response.start();
		try {
			response.setData(inquiryDetailService.getLatest(info));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询既往史失败！");
		}
		return response.success();
	}
	
}
