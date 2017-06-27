package com.lantone.icss.api.at.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.at.model.wrapper.DoctorInfoWrapper;

/**
 * @Description:医生信息服务
 * @author : luwg
 * @time : 2016年12月15日 下午7:05:53
 * 
 */
public interface DoctorInfoService extends BaseService<DoctorInfo, DoctorInfoWrapper, Long>{

	/**
	 * @Description:根据医生编号和医院编号获取医生信息
	 * @author:luwg
	 * @time:2016年12月18日 下午1:34:17
	 */
	public DoctorInfo getDoctorByNoAndHospital(String doctorNo, String hospitalNo);
	
	/**
	 * @Description:新建医生信息
	 * @author:luwg
	 * @time:2016年12月18日 下午1:45:30
	 */
	public Long insertDoctor(DoctorInfo doctor);
	
	/**
	 * @Description:更新医生信息
	 * @author:luwg
	 * @time:2016年12月18日 下午1:53:55
	 */
	public void updateDoctor(DoctorInfo doctor);
	
	/**
	 * @Description:修改或插入医生信息
	 * @author:CSP
	 * @time:2016年12月18日 下午1:53:55
	 */
	public void updateOrinsertDoctor(List<DoctorInfoWrapper> doctorInfoList);
}
