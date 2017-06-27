package com.lantone.icss.provider.at.dao;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.RecordDrug;
import com.lantone.icss.api.at.model.wrapper.RecordDrugWrapper;

public interface RecordDrugMapper extends EntityMapper<RecordDrug, RecordDrugWrapper, Long>{

	/**
	 * @Description:新增用药记录
	 * @author:luwg
	 * @time:2016年12月20日 上午11:27:43
	 */
	public void insertRecordDrug(RecordDrugWrapper recordDrug);
	
	/**
	 * @Description:根据问诊记录id删除用药记录
	 * @author:luwg
	 * @time:2016年12月20日 上午11:28:27
	 */
	public void deleteRecordDrugByRecordId(Long recordId);
}
