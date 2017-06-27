package com.lantone.icss.provider.at.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.InquiryDetail;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;

public interface InquiryDetailMapper extends EntityMapper<InquiryDetail, InquiryDetailWrapper, Long>{

	
	/**
	 * @Description:搜索
	 * @author:ztg
	 * @time:2017年5月24日 下午12:54:25
	 */
	public List<InquiryDetailWrapper> index(InquiryDetailWrapper detail);
	
	/**
	 * @Description: 添加明细
	 * @author:ztg
	 * @time:2017年3月29日 下午5:30:51
	 */
	public void insertDetail(List<InquiryDetailWrapper> detailList);
	
	
	/**
	 * @Description: 根据quiryId删除明细
	 * @author:ztg
	 * @time:2017年3月29日 下午5:31:33
	 */
	public void delDetailByQuiryId(Long quiryId);
	
	/**
	 * @Description: 搜索明细
	 * @author:ztg
	 * @time:2017年3月30日 下午1:41:55
	 */
	public List<InquiryDetailWrapper> findByInquiryId(Long quiryId);
	
	
	/**
	 * @Description:获取最新的(既往史:type=2)
	 * @author:ztg
	 * @time:2017年4月28日 上午11:03:12
	 */
	public List<InquiryDetailWrapper> getLatest(InquiryInfoWrapper info);
	
	/**
	 * @Description:获取疾病历史
	 * @author:CSP
	 * @time:2017年5月24日 上午11:03:12
	 */
	public List<InquiryDetailWrapper> getDiseaseByInquery(InquiryInfoWrapper info);
}
