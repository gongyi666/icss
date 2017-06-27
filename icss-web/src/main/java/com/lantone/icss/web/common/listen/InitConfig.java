package com.lantone.icss.web.common.listen;

import java.util.HashMap;

public class InitConfig {

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
