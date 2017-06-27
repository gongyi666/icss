package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.PacsApparatus;
import com.lantone.icss.api.kl.model.wrapper.ApparatusPartWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsApparatusWrapper;

public interface PacsApparatusMapper extends EntityMapper<PacsApparatus, PacsApparatusWrapper, Long>{

	/**
	 * @Description:根据id获取器械信息
	 * @author:luwg
	 * @time:2017年3月2日 下午3:53:19
	 */
	public List<PacsApparatusWrapper> getPacsApparatusByCode(@Param("code") String code);
	
	/**
	 * @Description:根据部位获取器械
	 * @author:luwg
	 * @time:2017年3月3日 上午11:53:59
	 */
	public List<PacsApparatusWrapper> getPacsApparatusByPartCode(@Param("partCode") String partCode);
	
	/**
	 * @Description:根据部位和器官获取器械信息
	 * @author:luwg
	 * @time:2017年3月7日 下午2:31:24
	 */
	public List<PacsApparatusWrapper> getApparatusByPartAndOrgan(Map<String,Object> paramMap);
	
	/**
	 * @Description:根据器官获取器械
	 * @author:luwg
	 * @time:2017年3月3日 上午11:54:48
	 */
	public List<PacsApparatusWrapper> getPacsApparatusByOrganCode(@Param("organCode") String organCode);
	
	/**
	 * @Description:获取pacs内容
	 * @author:luwg
	 * @time:2017年3月8日 下午6:33:33
	 */
	public List<ApparatusPartWrapper> getApparatusPart(Map<String,Object> paramMap);
	
	/**
	 * @Description:根据器械类型获取器械
	 * @author:luwg
	 * @time:2017年3月9日 上午11:45:49
	 */
	public List<PacsApparatusWrapper> getPacsApparatusByType(Long typeId);
	
	/**
	 * @Description:根据部位id获取手段
	 * @author:ztg
	 * @time:2017年4月19日 上午10:31:56
	 */
	public List<PacsApparatusWrapper> getByPartId(Long partId);
	
	
}
