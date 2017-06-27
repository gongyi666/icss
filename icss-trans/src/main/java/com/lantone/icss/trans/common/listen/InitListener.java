/**   
* @Company: 杭州朗通信息技术有限公司 
* @Department: 系统软件部 
* @Description: 朗通智能辅助诊疗系统 
* @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
*/
package com.lantone.icss.trans.common.listen;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lantone.core.utils.PropertiesUtil;



/**
* @Title: InitListener.java 
* @Package com.lantone.common.listen 
* @Description: 初始化系统数据 
* @author cm   
* @date 2015年4月23日 下午11:03:28 
* @version V1.0
 */
public class InitListener implements javax.servlet.ServletContextListener {
	/**日志记录*/
	Logger logger = LoggerFactory.getLogger(InitListener.class);
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	/**
	 * 开始初始化数据
	 * 
	 * @return
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
//		contextDeseaseInfoInitialized(event);//初始化疾病信息
//		contextPhyPhysicInfoInitialized(event);//初始化药品信息
//		contextInspectLisInitialized(event);//初始化Lis信息
//		contextInspectPacsInitialized(event);//初始化Pacs信息
		sysParamsInitialized(event);//初始化系统数据
	}
	
//	private void contextInspectPacsInitialized(ServletContextEvent event) {
//		try {
//			InspectPacsCacheUtil inspectPacsCacheUtil = SpringContextUtil.getBean(InspectPacsCacheUtil.class);
//			inspectPacsCacheUtil.flushCodeToInspectPacsIdCache();
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("药品信息初始化出错!", e);
//		}
//	}
//	private void contextInspectLisInitialized(ServletContextEvent event) {
//		try {
//			InspectLisCacheUtil inspectLisCacheUtil = SpringContextUtil.getBean(InspectLisCacheUtil.class);
//			inspectLisCacheUtil.flushPhysicInfoIntoCache();
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("药品信息初始化出错!", e);
//		}
//	}
//	private void contextPhyPhysicInfoInitialized(ServletContextEvent event) {
//		try {
//			PhysicInfoCacheUtil physicInfoCacheUtil = SpringContextUtil.getBean(PhysicInfoCacheUtil.class);
//			physicInfoCacheUtil.flushPhysicInfoIntoCache();
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("药品信息初始化出错!", e);
//		}
//	}
//	public void contextDeseaseInfoInitialized(ServletContextEvent event) {
//		try {
//			DeseaseInfoCacheUtil deseaseInfoCacheUtil = SpringContextUtil.getBean(DeseaseInfoCacheUtil.class);
//			deseaseInfoCacheUtil.flushIcdToDeseaseIdCache();;
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("疾病信息初始化出错!", e);
//		}
//	}	
//	
	/**
	 * 
	 * @Title: sysParamsInitialized
	 * @Description:  初始化系统数据 
	 * @author 蔡苗
	 * @param event 
	 * @since JDK 1.7
	 */
	public void sysParamsInitialized(ServletContextEvent event) {
		try {
			PropertiesUtil pl = new PropertiesUtil("classpath:/webservice.properties");
			for(Object object : pl.getProperties().keySet()){
				InitConfig.set((String)object, pl.getProperties().getProperty((String)object));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化系统数据 出错!", e);
		}
	}
}
