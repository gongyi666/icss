package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PacsPosition;
import com.lantone.icss.api.kl.model.wrapper.PacsPositionWrapper;

public interface PacsPositionMapper extends EntityMapper<PacsPosition, PacsPositionWrapper, Long>{

	public List<PacsPositionWrapper> getPacsPositionByDiseaseId(Long diseaseId);
	
	/**
	 * @Description:获取默认pacs部位信息
	 * @author:luwg
	 * @time:2017年1月18日 下午2:20:42
	 */
	public List<PacsPositionWrapper> getDefaultPacsPosition();
	
	/**
	 * @Description:获取所有pacs部位
	 * @author:luwg
	 * @time:2017年1月20日 上午9:24:17
	 */
	public List<PacsPositionWrapper> getAllPacsPosition();
	
	/**
	 * @Description:根据id获取pacs部位
	 * @author:luwg
	 * @time:2017年1月20日 上午9:26:48
	 */
	public PacsPositionWrapper getPacsPositionById(Long positionId);
}
