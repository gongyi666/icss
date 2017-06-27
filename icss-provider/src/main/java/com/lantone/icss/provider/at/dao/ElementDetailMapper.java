package com.lantone.icss.provider.at.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.ElementDetail;
import com.lantone.icss.api.at.model.wrapper.ElementDetailWrapper;

public interface ElementDetailMapper extends EntityMapper<ElementDetail, ElementDetailWrapper, Long>{

	/**
	 * @Description:新增元素明细
	 * @author:luwg
	 * @time:2017年1月11日 下午3:50:47
	 */
	public void insertElementDetail(ElementDetailWrapper elementDetail);
	
	/**
	 * @Description:删除元素明细
	 * @author:luwg
	 * @time:2017年1月11日 下午3:52:16
	 */
	public void deleteElementDetail(Long recordId);
	
	/**
	 * @Description:查询元素明细
	 * @author:luwg
	 * @time:2017年1月11日 下午3:53:00
	 */
	public List<ElementDetailWrapper> selectElementDetail(Long elementId);
}
