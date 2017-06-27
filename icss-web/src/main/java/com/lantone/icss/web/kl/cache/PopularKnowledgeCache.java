package com.lantone.icss.web.kl.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lantone.icss.api.kl.model.PopularKnowledge;
import com.lantone.icss.api.kl.service.PopularKnowledgeService;
import com.lantone.icss.web.common.util.LanToneConstant;

@Component
public class PopularKnowledgeCache {
	private static Logger logger = LoggerFactory.getLogger(PopularKnowledgeCache.class);
	
	PopularKnowledgeService	popularKnowledgeService;
	Map<String,PopularKnowledge> popularKnowledgeMap = new HashMap<String, PopularKnowledge>();
	/**
	 * 加载词库诊断依据映射关系
	 */
	public void flushPopularKnowledges() throws Exception {
		try {
			List<PopularKnowledge> popularKnowledges = popularKnowledgeService.selectAllKnowledge();// 查询词库所有词
			for (PopularKnowledge popularKnowledge : popularKnowledges) {
				if (popularKnowledge != null) {
//					popularKnowledgeMap.put(LanToneConstant.NounId_Cache+popularKnowledge.getNounId()+LanToneConstant.DescribeId_Cache+popularKnowledge.getDescribeId(), popularKnowledge);
				}
			}
		} catch (Exception e) {
			logger.error("加载词库诊断依据映射关系");
		}
		
	}
	/**
	 * 获取词库诊断依据映射关系
	 */
	public PopularKnowledge  getPopularKnowledge(Long nounId,Long describeId){
		PopularKnowledge popularKnowledge = new PopularKnowledge();
		popularKnowledge = popularKnowledgeMap.get(LanToneConstant.NounId_Cache+nounId+LanToneConstant.DescribeId_Cache+describeId);

		if (popularKnowledge == null) {
			popularKnowledge = getPopularKnowledgeFromDb(nounId,describeId);
		}
		return popularKnowledge;
	}
	
	/**
	 * 根据名词id和描述词id查询
	 * 
	 * @return
	 */
	public PopularKnowledge getPopularKnowledgeFromDb(Long nounId,Long describeId) {
		PopularKnowledge popularKnowledge = new PopularKnowledge();
		popularKnowledge = popularKnowledgeService.selectPopularKnowledgeByName(nounId,describeId);// 从数据库查询
		if (popularKnowledge != null) {
			
//			popularKnowledgeMap.put(LanToneConstant.NounId_Cache+popularKnowledge.getNounId()+LanToneConstant.DescribeId_Cache+popularKnowledge.getDescribeId(), popularKnowledge);
		}
		return popularKnowledge;
	}
}
