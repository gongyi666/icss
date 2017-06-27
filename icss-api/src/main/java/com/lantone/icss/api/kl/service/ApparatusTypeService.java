package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.ApparatusType;
import com.lantone.icss.api.kl.model.wrapper.ApparatusTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;

public interface ApparatusTypeService extends BaseService<ApparatusType, ApparatusTypeWrapper, Long>{

	/**
	 * @Description:获取器械分类（包含下面的器械）
	 * @author:luwg
	 * @time:2017年3月9日 上午11:38:13
	 */
	public List<ApparatusTypeWrapper> getApparatusType();
	
	/**
	 * @Description:根据部位获取手段类型及明细
	 * @author:ztg
	 * @time:2017年4月7日 上午9:53:54
	 */
	public List<ApparatusTypeWrapper> getBypartCode(String partCode);
}
