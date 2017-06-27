package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PacsPart;
import com.lantone.icss.api.kl.model.wrapper.PacsPartWrapper;

public interface PacsPartMapper extends EntityMapper<PacsPart, PacsPartWrapper, Long>{

	/**
	 * @Description:根据器械id获取部位信息
	 * @author:luwg
	 * @time:2017年3月2日 下午4:16:33
	 */
	public List<PacsPartWrapper> getPacsPartByApparatusCode(@Param("apparatusCode") String apparatusCode);
	
	/**
	 * @Description:根据编码获取部位信息
	 * @author:luwg
	 * @time:2017年3月3日 上午10:19:44
	 */
	public List<PacsPartWrapper> getPacsPartByCode(@Param("code") String code);
	
	/**
	 * @Description:根据器械和器官获取部位信息
	 * @author:luwg
	 * @time:2017年3月3日 上午10:44:03
	 */
	public List<PacsPartWrapper> getPacsPartByApparatusAndOrgan(Map<String,Object> paramMap);
	
	/**
	 * @Description:根据器官获取部位信息
	 * @author:luwg
	 * @time:2017年3月3日 上午11:59:48
	 */
	public List<PacsPartWrapper> getPacsPartByOrganCode(@Param("organCode") String organCode);
	
	/**
	 * @Description:获取第一层部位
	 * @author:luwg
	 * @time:2017年3月3日 下午3:57:53
	 */
	public List<PacsPartWrapper> getPacsPartLeveLOne();
	
	/**
	 * @Description:根据上一级id获取下一级部位
	 * @author:luwg
	 * @time:2017年3月3日 下午4:04:33
	 */
	public List<PacsPartWrapper> getPacsPartByParentId(Long parentId);



	/**
	 * @Description: 根据主键获取
	 * @author:luwg
	 * @time:2017年3月3日 下午4:04:33
	 */
	public PacsPartWrapper selectByPrimaryKey(Long id);
	
	
} 
