package com.lantone.icss.provider.kl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.TransverseInfo;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDiseaseWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithLisWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithPacsWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWrapper;

public interface TransverseInfoMapper extends EntityMapper<TransverseInfo, TransverseInfoWrapper, Long>{

	/**
	 * @Description:根据类型获取横向菜单
	 * @author:luwg
	 * @time:2017年2月24日 上午10:52:37
	 */
	public List<TransverseInfoWrapper> getTransverseInfoByType(@Param("type") String type);

	/**
	 * @Description:根据类型获取横向菜单(药)
	 * @author:ztg
	 * @time:2017年3月14日 上午21:52:37
	 */
	public List<TransverseInfoWithDrugWrapper> getTransverseInfoWithDrug(@Param("type") String type);

	/**
	 * @Description:根据类型获取横向菜单(化验)
	 * @author:ztg
	 * @time:2017年4月18日 下午5:17:38
	 */
	public List<TransverseInfoWithLisWrapper> getTransverseInfoWithLis(@Param("type") String type);

	
	/**
	 * @Description:根据类型获取横向菜单(器查)
	 * @author:ztg
	 * @time:2017年4月18日 下午5:17:38
	 */
	public List<TransverseInfoWithPacsWrapper> getTransverseInfoWithPacs(@Param("type") String type);
	
	
	/**
	 * @Description:根据类型获取横向菜单(诊断)
	 * @author:ztg
	 * @time:2017年4月18日 下午5:17:38
	 */
	public List<TransverseInfoWithDiseaseWrapper> getTransverseInfoWithDisease(@Param("type") String type);

}
