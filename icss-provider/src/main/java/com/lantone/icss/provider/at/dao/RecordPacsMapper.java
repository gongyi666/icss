package com.lantone.icss.provider.at.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.RecordPacs;
import com.lantone.icss.api.at.model.wrapper.RecordPacsWrapper;

public interface RecordPacsMapper extends EntityMapper<RecordPacs, RecordPacsWrapper, Long>{

	/**
	 * @Description:新增问诊记录影像学检查结果
	 * @author:luwg
	 * @time:2016年12月19日 下午5:30:49
	 */
	public void insertRecordPacs(RecordPacsWrapper recordPacs);
	
	/**
	 * @Description:根据问诊记录id删除问诊记录影像学检查结果
	 * @author:luwg
	 * @time:2016年12月19日 下午5:31:55
	 */
	public void deleteRecordPacsByRecordId(Long recordId);
	
	/**
	 * @Description:获取问诊记录影像学检查信息
	 * @author:luwg
	 * @time:2016年12月29日 下午7:16:35
	 */
	public List<RecordPacsWrapper> getRecordPacsByRecordId(Long recordId);
}
