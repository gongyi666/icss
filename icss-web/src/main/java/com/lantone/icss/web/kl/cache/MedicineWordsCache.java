package com.lantone.icss.web.kl.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.icss.api.kl.model.KeyWord;
import com.lantone.icss.api.kl.service.KeyWordServcie;

/**
 * @author 吴文俊
 * @data   2016年12月12日
 * 杭州朗通信息技术有限公司
 * @describe 初始化医学词汇术语
 */
@Component
public class MedicineWordsCache {
	
	private static Logger logger = LoggerFactory.getLogger(MedicineWordsCache.class);
	

	@Reference
	KeyWordServcie keyWordServcie;

	//医学符号英文词Map
	Map<String, KeyWord> medicineWordsMapCache= new HashMap<String, KeyWord>();
	
	/**
	 * 加载英文符号医学术语
	 */
	public void flushMedicineWords() throws Exception {
		try{
		List<KeyWord> medicineWords = keyWordServcie.selectMedicineWords();//查询医学符号英文词
		for(KeyWord keyWord:medicineWords){
			if(keyWord!=null){
				medicineWordsMapCache.put(keyWord.getName(), keyWord);
			}
		}
		}catch(Exception e){
			logger.error("加载英文符号医学术语错误");
		}		
	}
	/**
	 * 获取英文符号医学术语
	 */
	public KeyWord getmedicineWordByName(String name){
		KeyWord keyWord = new KeyWord();
		keyWord=medicineWordsMapCache.get(name);
		
		if(keyWord==null){
			keyWord = getmedicineWordFromDb(name);			
		}
		return keyWord;
	}
	/**
	 * 根据英文符号名称查询数据库术语
	 * @param name
	 * @return
	 */
	public KeyWord getmedicineWordFromDb(String name){
		KeyWord keyWord = new KeyWord();
		keyWord=keyWordServcie.selectMedicineWordByName(name);//从数据库查询	
		if(keyWord!=null){
			medicineWordsMapCache.put(name, keyWord);
		}
		return keyWord;
	}
	public Map<String, KeyWord> getMedicineWordsMapCache() {
		return this.medicineWordsMapCache;
	}
}
	
