package com.lantone.icss.provider.common.listen.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lantone.core.service.RedisService;
import com.lantone.icss.api.at.model.Diagnose;

public class DiseaseForDiagnoseCacheUtil {
	
	@Autowired
	RedisService<String, List<Diagnose>> redisServiceDictionaryCache;
	
	private static Logger logger = LoggerFactory.getLogger(DiseaseForDiagnoseCacheUtil.class);

}
