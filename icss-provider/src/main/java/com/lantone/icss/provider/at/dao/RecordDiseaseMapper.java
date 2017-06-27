package com.lantone.icss.provider.at.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.RecordDisease;
import com.lantone.icss.api.at.model.wrapper.RecordDiseaseWrapper;

public interface RecordDiseaseMapper extends EntityMapper<RecordDisease, RecordDiseaseWrapper, Long>{

	/**
	 * @Description:问诊记录诊断结果
	 * @author:luwg
	 * @time:2016年12月19日 下午4:17:05
	 */
	public void insertRecordDisease(RecordDiseaseWrapper recordDisease);
	
	/**
	 * @Description:根据问诊记录id删除问诊诊断结果
	 * @author:luwg
	 * @time:2016年12月19日 下午4:22:15
	 */
	public void deleteRecordDiseaseByRecordId(Long recordId);
	
	/**
	 * @Description:获取问诊记录诊断数据
	 * @author:luwg
	 * @time:2016年12月29日 下午7:20:48
	 */
	public List<RecordDiseaseWrapper> getRecordDiseaseByRecordId(Long recordId); 
}
