package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PacsOrgan;
import com.lantone.icss.api.kl.model.wrapper.PacsOrganWrapper;

public interface PacsOrganMapper extends EntityMapper<PacsOrgan, PacsOrganWrapper, Long>{

	/**
	 * @Description:根据器械id获取器官信息
	 * @author:luwg
	 * @time:2017年3月3日 上午9:54:52
	 */
	public List<PacsOrganWrapper> getPacsOrganByApparatusCode(@Param("apparatusCode") String apparatusCode);
	
	/**
	 * @Description:根据器械和部位获取器官信息
	 * @author:luwg
	 * @time:2017年3月3日 上午10:24:06
	 */
	public List<PacsOrganWrapper> getPacsOrganByApparatusAndPart(Map<String,Object> paramMap);
	
	/**
	 * @Description:根据编码获取器官信息
	 * @author:luwg
	 * @time:2017年3月3日 上午10:28:20
	 */
	public List<PacsOrganWrapper> getPacsOrganByCode(@Param("code") String code);
	
	/**
	 * @Description:根据部位获取器官
	 * @author:luwg
	 * @time:2017年3月3日 下午1:12:28
	 */
	public List<PacsOrganWrapper> getPacsOrganByPartCode(@Param("partCode") String partCode);
} 
