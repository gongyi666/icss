package com.lantone.icss.provider.at.dao;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.DoctorSymptomCount;
import com.lantone.icss.api.at.model.wrapper.DoctorSymptomCountWrapper;

public interface DoctorSymptomCountMapper extends EntityMapper<DoctorSymptomCount, DoctorSymptomCountWrapper, Long>{

	/**
	 * @Description:查询医生是否已使用过该症状
	 * @author:luwg
	 * @time:2016年12月28日 上午10:35:33
	 */
	public DoctorSymptomCount getDoctorSymptomCount(DoctorSymptomCountWrapper docSymCount);
	
	/**
	 * @Description:新建医生使用症状记录
	 * @author:luwg
	 * @time:2016年12月28日 上午10:29:21
	 */
	public void insertDoctorSymptomCount(DoctorSymptomCountWrapper docSymCount);
	
	/**
	 * @Description:修改医生使用症状记录
	 * @author:luwg
	 * @time:2016年12月28日 上午10:30:00
	 */
	public void updateDoctorSymptomCount(DoctorSymptomCountWrapper docSymCount);
}
