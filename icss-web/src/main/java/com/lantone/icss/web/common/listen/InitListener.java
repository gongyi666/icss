/**   
* @Company: 杭州朗通信息技术有限公司 
* @Department: 系统软件部 
* @Description: 朗通智能辅助诊疗系统 
* @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
*/
package com.lantone.icss.web.common.listen;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;

import com.lantone.core.utils.PropertiesUtil;
import com.lantone.core.utils.spring.SpringContextUtil;
import com.lantone.icss.web.kl.cache.DiseaseInfoWithDiagnoseInfoCache;
import com.lantone.icss.web.kl.cache.KeyWordsCache;
import com.lantone.icss.web.kl.cache.LisTypeCacheUtil;
import com.lantone.icss.web.kl.cache.MedicineWordsCache;


/**
 * @Title: InitListener.java
 * @Package com.lantone.starry.common.listen
 * @Description: 初始化系统数据
 * @author 楼辉荣(Fyeman)
 * @date 2016年8月23日 下午11:03:28
 * @version V1.0
 */
public class InitListener implements javax.servlet.ServletContextListener {
	/** 日志记录 */
	Logger log = Logger.getLogger(InitListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * 开始初始化数据
	 * 
	 * @return
	 */
	public void contextInitialized(ServletContextEvent event) {
		initSystemInfo();
		initDiseaseInfoWithDiagnoseInfo();
//		initMedicineWords();
//		initKeyWords();
//		initLisType();
//		initPopularKnowledge();
	}



	/**
	 * 初始化关键词表
	 */
	private void initKeyWords() {
		try{
			KeyWordsCache keyWordsCache = SpringContextUtil.getBean(KeyWordsCache.class);
			keyWordsCache.flushKeyWords();
		} catch (Exception e) {
			log.error("初始化关键词失败");
		}
	}
	
	public void initLisType(){
		try {
			LisTypeCacheUtil LlisTypeCacheUtil = SpringContextUtil.getBean(LisTypeCacheUtil.class);
			LlisTypeCacheUtil.flushLisTypeIntoCache();
		} catch (Exception e) {
			log.error("初始化检验类别失败");
		}
	}

	/**
	 * 初始化医学术语
	 * 
	 */
	private void initMedicineWords() {
		try{
			MedicineWordsCache medicineWordsCatch = SpringContextUtil.getBean(MedicineWordsCache.class);
			medicineWordsCatch.flushMedicineWords();
		} catch (Exception e) {
			log.error("初始化医学术语失败");
		}
	}
	/**
	 * 初始化依据表
	 */
			
	private void initPopularKnowledge() {
		// TODO Auto-generated method stub
		
	}
	//初始化系统参数
	private void initSystemInfo(){
		try {
			PropertiesUtil pl = new PropertiesUtil("classpath:/webservice.properties");
			for(Object object : pl.getProperties().keySet()){
				InitConfig.set((String)object, pl.getProperties().getProperty((String)object));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("初始化系统数据 出错!", e);
		}
	}
	
	/**
	 * @author 杨关
	 * 初始化关键词表
	 */
	private void initDiseaseInfoWithDiagnoseInfo(){
		try{
			DiseaseInfoWithDiagnoseInfoCache dc = SpringContextUtil.getBean(DiseaseInfoWithDiagnoseInfoCache.class);
			dc.flushDiseaseInfoWithDiagnoseInfo();
			
		}catch(Exception e){
			log.error("初始化诊断依据失败");
		}
	}

}
