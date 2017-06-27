package com.lantone.icss.provider.at.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.InquiryLog;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryLogWrapper;

public interface InquiryLogMapper extends EntityMapper<InquiryLog, InquiryLogWrapper, Long>{

	
	/**
	 * @Description: 添加明细
	 * @author:ztg
	 * @time:2017年3月29日 下午5:30:51
	 */
	public void insertLog(List<InquiryLogWrapper> LogList);
	
	
	/**
	 * @Description:搜索
	 * @author:ztg
	 * @time:2017年5月24日 上午10:42:39
	 */
	public List<InquiryLogWrapper> index(InquiryLogWrapper info);
	
	/*
	*//**
	 * @Description: 根据quiryId删除明细
	 * @author:ztg
	 * @time:2017年3月29日 下午5:31:33
	 *//*
	public void delDetailByQuiryId(Long quiryId);
	
	*//**
	 * @Description: 搜索明细
	 * @author:ztg
	 * @time:2017年3月30日 下午1:41:55
	 *//*
	public List<InquiryDetailWrapper> findByInquiryId(Long quiryId);
	
	
	*//**
	 * @Description:获取最新的(既往史:type=2)
	 * @author:ztg
	 * @time:2017年4月28日 上午11:03:12
	 *//*
	public List<InquiryDetailWrapper> getLatest(InquiryInfoWrapper info);*/
}
