package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.PacsPosition;
import com.lantone.icss.api.kl.model.wrapper.PacsPositionWrapper;

/**
 * @Description:pacs部位
 * @author : luwg
 * @time : 2017年1月9日 下午1:48:53
 * 
 */
public interface PacsPositionService extends BaseService<PacsPosition, PacsPositionWrapper, Long>{

	/**
	 * @Description:根据疾病获取pacs部位
	 * @author:luwg
	 * @time:2017年1月9日 下午1:49:49
	 */
	public Map<String,Object> getPacsPositionByDiseaseIds(Long[] diseaseIds);
	
	/**
	 * @Description:获取所有的pacs部位
	 * @author:luwg
	 * @time:2017年1月20日 上午9:22:23
	 */
	public List<PacsPositionWrapper> getAllPacsPosition();
	
	/**
	 * @Description:根据id获取pacs部位
	 * @author:luwg
	 * @time:2017年1月20日 上午9:28:55
	 */
	public PacsPositionWrapper getPacsPositionById(Long positionId);
}
