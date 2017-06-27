package com.lantone.icss.web.at.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.wrapper.InquiryMsgWrapper;
import com.lantone.icss.api.at.service.InquiryMsgService;


/**
 * @Description:最终问诊记录
 * @author:csp
 * @time:2017年6月20日 上午16:31:10
 */
@Controller
@RequestMapping("/at/inquiry_msg")
public class InquiryMsgController {

	private static Logger logger = LoggerFactory.getLogger(InquiryMsgController.class);
	
	@Reference 
	InquiryMsgService inquiryMsgService;
	
	/**
	 * @Description: 保存his最终问诊记录到Icss
	 * @author:csp
	 * @time:2017年6月20日 上午16:32:49
	 */
	@RequestMapping(value="/insert_his_to_local", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String  insertHisToLocal(InquiryMsgWrapper infomsg){
		Response<String> response = new Response<String>();
		response.start();
		String msg="";
		try {
			Long localId = inquiryMsgService.insert(infomsg);
			if(localId != null) {
				msg="保存成功！";
			} else {
				return msg="保存失败！";
			}
			response.setData(msg);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
	
}
