package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.PacsPart;
import com.lantone.icss.api.kl.model.wrapper.PacsPartWrapper;


public interface PacsPartService extends BaseService<PacsPart, PacsPartWrapper, Long>{

	/**
	 * @Description:获取检查部位信息（包含器官）
	 * @author:luwg
	 * @time:2017年3月3日 下午3:42:14
	 */
	public List<PacsPartWrapper> getPacsPart();
	
	/**
	 * @Description:根据器械编码获取对应的部位信息
	 * @author:luwg
	 * @time:2017年3月12日 上午11:06:26
	 */
	public List<PacsPartWrapper> getPacsPartByApparatusCode(String apparatusCode);

	/**
	 * @Description:通过parentId获取部位信息
	 * @author:ztg
	 * @time:2017年4月6日 下午5:27:55
	 */
	public List<PacsPartWrapper> getByParentId(Long id);
}
