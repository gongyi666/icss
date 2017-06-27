package com.lantone.icss.provider.at.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.at.model.InquiryMsg;
import com.lantone.icss.api.at.model.wrapper.InquiryMsgWrapper;
import com.lantone.icss.api.at.service.InquiryMsgService;
import com.lantone.icss.provider.at.dao.InquiryMsgMapper;

@Service
public class InquiryMsgServiceImpl extends BaseServiceImpl<InquiryMsg, InquiryMsgWrapper, Long> implements InquiryMsgService{

	@Autowired
	InquiryMsgMapper inquiryMsgMapper;

	@Override
	public Long insert(InquiryMsgWrapper infomsg) {
		// TODO Auto-generated method stub
		Long id = null;
		inquiryMsgMapper.insertMsg(infomsg);
		id=infomsg.getId();
		return id;
	}


}
