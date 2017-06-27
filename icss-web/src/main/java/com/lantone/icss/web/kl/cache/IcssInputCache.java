package com.lantone.icss.web.kl.cache;

import com.lantone.icss.web.kl.cds.IcssInput;
import com.lantone.icss.web.kl.cds.JsonRedisSeriazilerUtils;
import org.opencds.vmr.v1_0.schema.CDSInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lantone.core.service.RedisService;
@Component
public class IcssInputCache {
	@Autowired
	private RedisService<String, String> icssInputCache;

	public RedisService<String, String> getIcssInputCache() {
		return icssInputCache;
	}

	public void setIcssInputCache(RedisService<String, String> icssInputCache) {
		this.icssInputCache = icssInputCache;
	}
	
	public IcssInput get(String uuid) {
		String object = icssInputCache.get(uuid);
		IcssInput icssInput = JsonRedisSeriazilerUtils.deserializeAsObject(object, IcssInput.class);
		if (icssInput == null) {
			return new IcssInput();
		}
		return icssInput;
	}
	
	public void put(String uuid, IcssInput icssInput) {
		String object = JsonRedisSeriazilerUtils.seriazileAsString(icssInput);
		icssInputCache.add(uuid, object);
	}

	public void delete(String uuid){
		icssInputCache.delete(uuid);
	}
}
