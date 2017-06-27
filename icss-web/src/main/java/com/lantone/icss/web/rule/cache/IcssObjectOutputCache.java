package com.lantone.icss.web.rule.cache;

import com.lantone.icss.web.kl.cds.IcssInput;
import com.lantone.icss.web.kl.cds.JsonRedisSeriazilerUtils;
import com.lantone.icss.web.rule.model.IcssObjectOutput;

import org.opencds.vmr.v1_0.schema.CDSInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lantone.core.service.RedisService;
@Component
public class IcssObjectOutputCache {
	@Autowired
	private RedisService<String, String> icssObjectOutputCache;
	
	public RedisService<String, String> getIcssObjectOutputCache() {
		return icssObjectOutputCache;
	}

	public void setIcssObjectOutputCache(RedisService<String, String> icssObjectOutputCache) {
		this.icssObjectOutputCache = icssObjectOutputCache;
	}

	public IcssObjectOutput get(String uuid) {
		String object = icssObjectOutputCache.get(uuid);
		IcssObjectOutput icssObjectOutput = JsonRedisSeriazilerUtils.deserializeAsObject(object, IcssObjectOutput.class);
		if (icssObjectOutput == null) {
			return new IcssObjectOutput();
		}
		return icssObjectOutput;
	}
	
	public void put(String uuid, IcssObjectOutput icssObjectOutput) {
		String object = JsonRedisSeriazilerUtils.seriazileAsString(icssObjectOutput);
		icssObjectOutputCache.add(uuid, object);
	}

	public void delete(String uuid){
		icssObjectOutputCache.delete(uuid);
	}
}
