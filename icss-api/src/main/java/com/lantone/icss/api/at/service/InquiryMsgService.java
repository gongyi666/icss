package com.lantone.icss.api.at.service;


import com.lantone.core.service.BaseService;
import com.lantone.icss.api.at.model.InquiryMsg;
import com.lantone.icss.api.at.model.wrapper.InquiryMsgWrapper;

/**
 * @Description:最终问诊记录表
 * @author:csp
 * @time:2017年6月20日 下午1:50:12
 */
public interface InquiryMsgService extends BaseService<InquiryMsg, InquiryMsgWrapper, Long>{

	/**
	 * @Description:最终问诊记录表
	 * @author:csp
	 * @time:2017年6月20日 下午1:50:12
	 */
	public Long insert(InquiryMsgWrapper infomsg);
	
}
