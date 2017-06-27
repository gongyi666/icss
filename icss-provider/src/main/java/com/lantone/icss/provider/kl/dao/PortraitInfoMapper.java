package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PortraitInfo;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWrapper;

public interface PortraitInfoMapper extends EntityMapper<PortraitInfo, PortraitInfoWrapper, Long>{

	/**
	 * @Description:根据横向菜单获取纵向菜单
	 * @author:luwg
	 * @time:2017年2月24日 上午11:27:01
	 */
	public List<PortraitInfoWrapper> getPortraitByTransverse(Long transverseId);
	
	/**
	 * @Description:根据上级菜单获取下级菜单
	 * @author:luwg
	 * @time:2017年2月24日 上午11:27:53
	 */
	public List<PortraitInfoWrapper> getPortraitByParent(Long parentId);
	public PortraitInfoWrapper getPortraitById(Long Id);
	public PortraitInfoWrapper getPortraitByTypeAndGroup(Map<String,Object> map);
}
