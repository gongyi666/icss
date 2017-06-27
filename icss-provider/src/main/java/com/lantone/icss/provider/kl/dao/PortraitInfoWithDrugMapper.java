package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PortraitInfo;
import com.lantone.icss.api.kl.model.PortraitDrug;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWrapper;

public interface PortraitInfoWithDrugMapper extends EntityMapper<PortraitInfo, PortraitInfoWithDrugWrapper, Long>{

	/**
	 * @Description:根据上级菜单获取下级菜单（药）
	 * @author:ztg
	 * @time:2017年3月14日 上午21:57:01
	 */
	public List<PortraitInfoWithDrugWrapper> getPortraitWithDrugByParent(Long parentId);

	
	/**
	 * @Description:根据横向菜单获取纵向菜单（药）
	 * @author:ztg
	 * @time:2017年3月14日 上午21:57:01
	 */
	public List<PortraitInfoWithDrugWrapper> getPortraitWithDrugByTransverse(Long transverseId);
	public PortraitInfoWithDrugWrapper getPortraitById(Long portraitId);
	public void insertPortraitInfoDrugBatch(List<PortraitDrug> list);
	public void deletePortraitInfoDrugBatchBatch(List<PortraitDrug> list);
	public void deletePortraitInfoDrug(Map<String,Object> map);
}
