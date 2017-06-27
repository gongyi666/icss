package com.lantone.icss.provider.kl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.ApparatusType;
import com.lantone.icss.api.kl.model.wrapper.ApparatusTypeWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsApparatusWrapper;

public interface ApparatusTypeMapper extends EntityMapper<ApparatusType, ApparatusTypeWrapper, Long>{

	/**
	 * @Description:根据器械获取器械类型
	 * @author:luwg
	 * @time:2017年3月9日 上午9:50:10
	 */
	public List<ApparatusTypeWrapper> getTypeByApparatus(@Param("apparatusList") List<PacsApparatusWrapper> apparatusList);
	
	/**
	 * @Description:获取器械分类
	 * @author:luwg
	 * @time:2017年3月9日 上午11:42:35
	 */
	public List<ApparatusTypeWrapper> getApparatusType();
	
	
	/**
	 * @Description:根据部位获取手段类型及明细
	 * @author:ztg
	 * @time:2017年4月7日 上午10:03:05
	 */
	public List<ApparatusTypeWrapper> getBypartCode(String partCode);
}
