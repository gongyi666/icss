package com.lantone.icss.provider.at.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;

public interface PatientInfoMapper extends EntityMapper<PatientInfo, PatientInfoWrapper, Long>{

	/**
	 * @Description:根据患者编号和医院编号获取患者信息
	 * @author:luwg
	 * @time:2016年12月15日 下午7:18:40
	 */
	public List<PatientInfo> getPatientByNoAndHospital(Map<String,String> patientMap);
	
	/**
	 * @Description:保存患者信息
	 * @author:luwg
	 * @time:2016年12月18日 上午9:39:55
	 */
	public void insertPatient(PatientInfo patient);
	
	/**
	 * @Description:更新患者信息
	 * @author:luwg
	 * @time:2016年12月18日 上午11:01:46
	 */
	public void updatePatient(PatientInfo patient);
	
	/**
	 * @Description:根据id获取患者信息
	 * @author:luwg
	 * @time:2017年1月3日 下午4:14:50
	 */
	public PatientInfo getPatientById(Long patientId);

	/**
	 * @Description:根据查询条件获取排队列表
	 * @author:gaodm
	 * @time:2017年6月4日 下午4:14:50
     */
	List<PatientInfo> selectWaitListWrapper(Map<String,Object> map);
}
