package com.lantone.icss.api.at.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.at.model.InquiryInfo;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.at.model.wrapper.InquirySaveWrapper;

/**
 * @Description:问诊记录表
 * @author:ztg
 * @time:2017年3月29日 下午1:50:12
 */
public interface InquiryInfoService extends BaseService<InquiryInfo, InquiryInfoWrapper, Long>{

	/**
	 * @Description:添加问诊记录，返回主键值
	 * @author:ztg
	 * @time:2017年3月29日 下午2:12:51
	 */
	public Long insert(InquiryInfoWrapper info);
	
	
	/**
	 * @Description:添加问诊记录，返回主键值(改进版本)
	 * @author:ztg
	 * @time:2017年5月18日 下午4:07:18
	 */
	public Long insert(InquirySaveWrapper data);
	
	
	/**
	 * @Description: 搜索问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 上午11:48:13
	 */
	public List<InquiryInfoWrapper> index(InquiryInfoWrapper info);
	
	
	/**
	 * @Description:返回疾病和科室
	 * @author:ztg
	 * @time:2017年4月17日 下午2:26:21
	 */
	public Map<String,Object>  getDiseaseAndDept(InquiryInfoWrapper info);


	/**
	 * @Description:添加问诊记录，返回主键值
	 * @author:ztg
	 * @time:2017年3月29日 下午2:12:51
	 */
	public void updateInfo(InquiryInfoWrapper info);
	
	
	/**
	 * @Description: 搜索问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 上午11:48:13
	 */
	public String getLangtongInquiry(PatientInfo info);
}
