/**   
* @Company: 杭州朗通信息技术有限公司 
* @Department: 系统软件部 
* @Description: 朗通智能辅助诊疗系统 
* @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
*/
package com.lantone.icss.provider.common.listen;

import java.util.List;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lantone.core.utils.spring.SpringContextUtil;
import com.lantone.icss.provider.common.listen.util.DiseaseForDiagnoseCacheUtil;

/**
* @Title: InitListener.java 
* @Package com.lantone.icase.provider.common 
* @Description: 初始化系统数据 
* @author 楼辉荣(Fyeman)   
* @date 2016年8月23日 下午11:03:28 
* @version V1.0
 */
public class InitListener implements javax.servlet.ServletContextListener {
	/**日志记录*/
	Logger log = Logger.getLogger(InitListener.class);
	
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	/**
	 * 开始初始化数据
	 * 
	 * @return
	 */
	public void contextInitialized(ServletContextEvent event) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
                new String[] { "spring-registry.xml", "spring-mybatis.xml" });  
        context.start();  
	}
	
	/**
	* @Title: contextDictionaryInitialized 
	* @Description: 初始化诊断依据数据 
	* @param event 
	* @return void 
	* @throws
	 */
	public void contextDiseaseForDiagnose(ServletContextEvent event) {
		try {
			DiseaseForDiagnoseCacheUtil diseaseForDiagnoseCacheUtil = SpringContextUtil.getBean(DiseaseForDiagnoseCacheUtil.class);
			/**数据字典初始化*/
			/*List<Dictionary> dicts = SpringContextUtil.getBean(DictionaryService.class).listAll();
			dictionaryCacheUtil.flushDictionaryCache(dicts);*/
		} catch (Exception e) {
			log.error("读取数据字典错误!", e);
		}
	}
}
