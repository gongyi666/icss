package com.lantone.icss.web.kl.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.service.RedisService;
import com.lantone.icss.api.kl.model.wrapper.LisTypeWrapper;
import com.lantone.icss.api.kl.service.LisTypeService;

/**
 * @Description:lis类型缓存工具类(暂时未用)
 * @author : luwg
 * @time : 2017年1月19日 下午6:19:52
 * 
 */
@Component
public class LisTypeCacheUtil {
	
	private static Logger logger = LoggerFactory.getLogger(LisTypeCacheUtil.class);

	@Reference
	LisTypeService lisTypeService;
	@Autowired
	RedisService<String, LisTypeWrapper>  redisServiceLisTypeCache;
	
	private static final String LIS_TYPE_CACHE = "LIS_TYPE_CACHE_";
	
	/**
	 * @Description:将lis类型数据放入缓存
	 * @author:luwg
	 * @time:2017年1月19日 下午5:44:08
	 */
	public void flushLisTypeIntoCache() throws Exception{
		List<LisTypeWrapper> lisTypeList = lisTypeService.getAllLisType();
		for(LisTypeWrapper lisType : lisTypeList){
			addLisTypeIntoCache(lisType);
		}
	}
	
	/**
	 * @Description:从缓存中获取lis类型数据
	 * @author:luwg
	 * @time:2017年1月19日 下午6:05:49
	 */
	public LisTypeWrapper getLisTypeFromCache(Long typeId) throws Exception{
		LisTypeWrapper lisType = redisServiceLisTypeCache.get(LIS_TYPE_CACHE+typeId);
		if(lisType == null){
			lisType = getLisTypeFromDB(typeId);
		}
		return lisType;
	}
	
	/**
	 * @Description:从数据库中获取lis类型数据，并放入缓存中
	 * @author:luwg
	 * @time:2017年1月19日 下午6:06:05
	 */
	public LisTypeWrapper getLisTypeFromDB(Long typeId) throws Exception{
		try {
			LisTypeWrapper lisType = lisTypeService.getLisTypeById(typeId);
			if(lisType != null){
				addLisTypeIntoCache(lisType);
			}
			return lisType;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception("查询Lis类型出错");
		}
	}
	
	/**
	 * @Description:将lis类型数据放入缓存中
	 * @author:luwg
	 * @time:2017年1月19日 下午6:06:25
	 */
	public void addLisTypeIntoCache(LisTypeWrapper lisType){
		redisServiceLisTypeCache.add(LIS_TYPE_CACHE+lisType.getId(), lisType);
	}
	
}
