package com.lantone.icss.api.at.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.at.model.RecordElement;
import com.lantone.icss.api.at.model.wrapper.RecordElementWrapper;

public interface RecordElementService extends BaseService<RecordElement, RecordElementWrapper, Long>{

	/**
	 * @Description:还原结构化问诊
	 * @author:luwg
	 * @time:2017年1月11日 下午4:37:36
	 */
	public List<RecordElementWrapper> getRecordElement(Long recordId);
	
	/**
	 * @Description:根据type获取需要的历史数据
	 * @author:luwg
	 * @time:2017年1月15日 下午3:20:44
	 */
	public List<RecordElementWrapper> getRecordElementByType(Long recordId,String[] types);
}
