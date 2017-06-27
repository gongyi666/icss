package com.lantone.icss.rule.util;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 
* @ClassName: KieSessionFactory
* @Package com.lantone.icss.rule.util   
* @Description: TODO 
* @author 楼辉荣 (Fyeman)
* @date 2017年5月5日 下午1:52:06  
*
 */
public class KieSessionFactory {
	private static KieSession kSession;
	
	public static KieSession getInstance() {
		if (kSession == null) {
			KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
	    	kSession = kContainer.newKieSession("ksession-rules");
		}
    	return kSession;
	}
}
