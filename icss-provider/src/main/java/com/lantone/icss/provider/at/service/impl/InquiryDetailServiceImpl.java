package com.lantone.icss.provider.at.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.at.model.InquiryDetail;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.at.service.InquiryDetailService;
import com.lantone.icss.provider.at.dao.InquiryDetailMapper;

@Service
public class InquiryDetailServiceImpl extends BaseServiceImpl<InquiryDetail, InquiryDetailWrapper, Long> implements InquiryDetailService{

	@Autowired
	InquiryDetailMapper inquiryDetailMapper;
	
	@Override
	public List<InquiryDetailWrapper> findByInquiryId(InquiryDetailWrapper detail) {
		return inquiryDetailMapper.findByInquiryId(detail.getInquiryId());
	}

	@Override
	public List<InquiryDetailWrapper> getLatest(InquiryInfoWrapper info) {
		return inquiryDetailMapper.getLatest(info);
	}



}
