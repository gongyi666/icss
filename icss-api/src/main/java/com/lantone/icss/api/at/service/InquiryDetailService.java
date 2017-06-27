package com.lantone.icss.api.at.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.at.model.InquiryDetail;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;

/**
 * @Description:问诊记录明细表
 * @author:ztg
 * @time:2017年3月29日 下午1:51:57
 */
public interface InquiryDetailService extends BaseService<InquiryDetail, InquiryDetailWrapper, Long>{

	/**
	 * @Description:搜索(条件可扩展)
	 * @author:ztg
	 * @time:2017年3月30日 下午2:03:39
	 */
	public List<InquiryDetailWrapper> findByInquiryId(InquiryDetailWrapper detail);

	
	/**
	 * @Description:获取最新的既往史
	 * @author:ztg
	 * @time:2017年4月28日 上午11:03:12
	 */
	public List<InquiryDetailWrapper> getLatest(InquiryInfoWrapper info);


}
