/**   
* @Company: 杭州朗通信息技术有限公司 
* @Department: 系统软件部 
* @Description: 朗通智能辅助诊疗系统 
* @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
*/
package com.lantone.icss.trans.common.listen;

import java.util.HashMap;

/**
* @Title: InitConfig.java 
* @Package com.lantone.common.listen 
* @Description: 初始化系统数据 
* @author cm   
* @date 2015年10月09日 下午11:03:28 
* @version V1.0
 */
public class InitConfig {
	

	public static final String WEBSERVICE_URL  = "webservice.url";


	 /** 系统缓存Map */
    private static HashMap<String, String> sysParams = new HashMap<String, String>();
    
    
    /**
	* @Title: get 
	* @Description: 根据key从系统缓存取数据
	* @param key 
	* @return String 
	 */
    public static String get(String key) {
        return sysParams.get(key);
    }
    
    /**
   	* @Title: set 
   	* @Description: 把数据保存到缓存中
   	* @param key
   	* @param value
   	 */
    public static void set(String key, String value) {
        sysParams.put(key, value);
    }

  
    
    
	

}
