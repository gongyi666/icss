package com.lantone.icss.provider.kl.dao;

import java.util.List;
import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PortraitInfo;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithLisWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithPacsWrapper;

public interface PortraitInfoWithPacsMapper extends EntityMapper<PortraitInfo, PortraitInfoWithLisWrapper, Long>{

	/**
	 * @Description:根据横向菜单获取纵向菜单
	 * @author:luwg
	 * @time:2017年2月24日 上午11:27:01
	 */
	public List<PortraitInfoWithPacsWrapper> getPortraitWithPacsByTransverse(Long transverseId);
	
	/**
	 * @Description:根据上级菜单获取下级菜单
	 * @author:luwg
	 * @time:2017年2月24日 上午11:27:53
	 */
	public List<PortraitInfoWithPacsWrapper> getPortraitWithPacsByParent(Long parentId);
}
