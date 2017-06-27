package com.lantone.icss.provider.at.dao;

import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.at.model.wrapper.DoctorInfoWrapper;

public interface DoctorInfoMapper extends EntityMapper<DoctorInfo, DoctorInfoWrapper, Long>{

	/**
	 * @Description:根据医生编号和医院编号获取医生信息
	 * @author:luwg
	 * @time:2016年12月18日 下午1:39:15
	 */
	public DoctorInfo getDoctorByNoAndHospital(Map<String,String> doctorMap);
	
	/**
	 * @Description:新增医生信息
	 * @author:luwg
	 * @time:2016年12月18日 下午1:46:45
	 */
	public void insertDoctor(DoctorInfo doctor);
	
	/**
	 * @Description:更新医生信息
	 * @author:luwg
	 * @time:2016年12月18日 下午1:54:47
	 */
	public void updateDortor(DoctorInfo doctor);
}
