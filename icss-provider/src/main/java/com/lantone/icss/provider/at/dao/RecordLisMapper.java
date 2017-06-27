package com.lantone.icss.provider.at.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.RecordLis;
import com.lantone.icss.api.at.model.wrapper.RecordLisWrapper;

public interface RecordLisMapper extends EntityMapper<RecordLis, RecordLisWrapper, Long>{

	/**
	 * @Description:新增问诊记录检验检查结果
	 * @author:luwg
	 * @time:2016年12月19日 下午4:24:51
	 */
	public void insertRecordLis(RecordLisWrapper recordLis);
	
	/**
	 * @Description:根据问诊记录id删除问诊记录检验检查结果
	 * @author:luwg
	 * @time:2016年12月19日 下午4:37:43
	 */
	public void deleteRecordLisByRecordId(Long recordId);
	
	/**
	 * @Description:获取问诊记录检验检查信息
	 * @author:luwg
	 * @time:2016年12月29日 下午7:11:16
	 */
	public List<RecordLisWrapper> getRecordLisByRecordId(Long recordId);
}
