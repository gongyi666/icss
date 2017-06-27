package com.lantone.icss.api.at.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.at.model.RecordInfo;
import com.lantone.icss.api.at.model.wrapper.RecordInfoWrapper;

/**
 * @Description:问诊记录服务
 * @author : luwg
 * @time : 2016年12月19日 下午3:11:58
 * 
 */
public interface RecordInfoService extends BaseService<RecordInfo, RecordInfoWrapper, Long>{

	/**
	 * @Description:保存问诊记录
	 * @author:luwg
	 * @time:2016年12月20日 上午9:42:38
	 */
	public Long insertRecord(RecordInfoWrapper record) throws Exception;
	
	
	/**
	 * @Description:获取患者历史病历
	 * @author:luwg
	 * @time:2016年12月29日 下午6:27:22
	 */
	public List<RecordInfoWrapper> getRecordsByPatientId(Long patientId) throws Exception;
	
	/**
	 * @Description:根据疾病id获取历史病历信息
	 * @author:luwg
	 * @time:2016年12月29日 下午6:25:32
	 */
	public RecordInfoWrapper getHistoryRecordByRecordId(Long recordId) throws Exception;
	
	/**
	 * @Description:根据id获取问诊记录
	 * @author:luwg
	 * @time:2017年1月3日 下午4:06:05
	 */
	public RecordInfo getRecordById(Long recordId);
}
