package com.lantone.icss.provider.at.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.InquiryInfo;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;

public interface InquiryInfoMapper extends EntityMapper<InquiryInfo, InquiryInfoWrapper, Long>{
	
	
	/**
	 * @Description:通过主键查找记录
	 * @author:ztg
	 * @time:2017年3月30日 下午2:41:39
	 */
	public InquiryInfoWrapper selectByPrimaryKey(Long id);
	
	/**
	 * @Description:添加问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 下午2:41:39
	 */
	public Long insertInfo(InquiryInfoWrapper info);
	
	
	/**
	 * @Description:更新问诊记录
	 * @author:ztg
	 * @time:2017年3月30日 下午2:42:10
	 */
	public void updateInfo(InquiryInfoWrapper info);
	
	
	/**
	 * @Description: 搜索
	 * @author:ztg
	 * @time:2017年3月30日 下午2:42:32
	 */
	public List<InquiryInfoWrapper> index(InquiryInfoWrapper info);
	
	
	/**
	 * @Description:搜索疾病名称和编码
	 * @author:ztg
	 * @time:2017年4月17日 下午3:26:14
	 */
	public List<InquiryInfoWrapper> getNameAndId(InquiryInfoWrapper info);
	
	/**
	 * @Description: 搜索科室名称和编码
	 * @author:ztg
	 * @time:2017年3月30日 下午2:42:32
	 */
	public List<InquiryInfoWrapper> getDeptAndCode(InquiryInfoWrapper info);
}
