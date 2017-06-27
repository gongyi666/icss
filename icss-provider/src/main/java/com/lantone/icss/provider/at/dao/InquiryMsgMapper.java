package com.lantone.icss.provider.at.dao;


import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.InquiryMsg;
import com.lantone.icss.api.at.model.wrapper.InquiryMsgWrapper;

public interface InquiryMsgMapper extends EntityMapper<InquiryMsg, InquiryMsgWrapper, Long>{
	
	
	/**
	 * @Description:插入最终就诊记录
	 * @author:csp
	 * @time:2017年6月20日 下午2:41:39
	 */
	public InquiryMsgWrapper selectByPrimaryKey(Long id);
	
	/**
	 * @Description:插入最终就诊记录
	 * @author:csp
	 * @time:2017年6月20日 下午2:41:39
	 */
	public Long insertMsg(InquiryMsgWrapper infomsg);
	
}
