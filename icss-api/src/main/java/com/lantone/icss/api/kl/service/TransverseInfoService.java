package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.TransverseInfo;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDiseaseWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithLisWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithPacsWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWrapper;

/**
 * @Description:横向菜单服务
 * @author : luwg
 * @time : 2017年2月24日 上午10:25:47
 * 
 */
public interface TransverseInfoService extends BaseService<TransverseInfo, TransverseInfoWrapper, Long>{

	
	/**
	 * @Description:根据类型获取横向及其子项信息(参数过滤)
	 * @author:luwg
	 * @time:2017年2月24日 上午10:39:56
	 */
	public List<TransverseInfoWrapper> getTransverseByType(SubitemInfoWrapper param);
	
	
	/**
	 * @Description: 根据类型获取横向及子项（药）信息
	 * @author ztg
	 * @param type
	 * @time:2017年3月14日 上午20:03:56
	 */
	public List<TransverseInfoWithDrugWrapper> getTransverseWithDrug(String type);
	
	
	/**
	 * @Description:根据类型获取横向及子项（化验）信息
	 * @author:ztg
	 * @time:2017年4月18日 下午3:56:47
	 */
	public List<TransverseInfoWithLisWrapper> getTransverseWithLis(String type);


	/**
	 * @Description:根据类型获取横向及子项（化验）信息
	 * @author:ztg
	 * @time:2017年4月18日 下午3:56:47
	 */
	public List<TransverseInfoWithPacsWrapper> getTransverseWithPacs(String type);

	
	/**
	 * @Description:根据类型获取横向及子项（化验）信息
	 * @author:ztg
	 * @time:2017年4月18日 下午3:56:47
	 */
	public List<TransverseInfoWithDiseaseWrapper> getTransverseWithDisease(String type);


	public PortraitInfoWithDrugWrapper getPortraitInfoById(Long portraitId);
}
