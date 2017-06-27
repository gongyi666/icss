package com.lantone.icss.api.at.service;


import com.lantone.core.service.BaseService;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.WaitingListIn;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.api.at.model.wrapper.PatientWaitingInfoWrapper;

import java.util.List;

/**
 * @Description:患者信息服务
 * @author : luwg
 * @time : 2016年12月15日 下午7:03:58
 * 
 */
public interface PatientInfoService extends BaseService<PatientInfo, PatientInfoWrapper, Long>{

	/**
	 * @Description:根据患者编号和医院获取患者信息
	 * @author:luwg
	 * @time:2016年12月15日 下午7:13:12
	 */
	public PatientInfo getPatientByNoAndHospital(String patientNo,String hospitalNo);
	
	/**
	 * @Description:保存患者信息
	 * @author:luwg
	 * @time:2016年12月18日 上午9:37:25
	 */
	public Long insertPatient(PatientInfo patient);
	
	/**
	 * @Description:更新患者信息
	 * @author:luwg
	 * @time:2016年12月18日 上午9:59:39
	 */
	public void updatePatient(PatientInfo patient);
	
	/**
	 * @Description:根据id获取患者信息
	 * @author:luwg
	 * @time:2017年1月3日 下午4:13:39
	 */
	public PatientInfo getPatientById(Long patientId);

	/**
	 * @Description:获取就诊病人列表
	 * @author:gaodm
	 * @time: 2017年6月4日 下午4:14:50
	 */
	List<PatientWaitingInfoWrapper> getWaitingList(WaitingListIn waitingList);
}
