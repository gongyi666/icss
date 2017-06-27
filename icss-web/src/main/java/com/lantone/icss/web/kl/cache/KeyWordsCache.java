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

@Component
public class KeyWordsCache {

	private static Logger logger = LoggerFactory.getLogger(KeyWordsCache.class);
	@Reference
	KeyWordServcie keyWordServcie;

	// 词库Map
	Map<String, KeyWord> keyWordsMapCache = new HashMap<String, KeyWord>();

	/**
	 * 加载词库
	 */
	public void flushKeyWords() throws Exception {
		try {
			List<KeyWord> allWords = keyWordServcie.selectAllWords();// 查询词库所有词
			for (KeyWord keyWord : allWords) {
				if (keyWord != null) {
					keyWordsMapCache.put(keyWord.getName(), keyWord);
				}
			}
		} catch (Exception e) {
			logger.error("加载词库错误");
		}
	}

	/**
	 * 获取符号医学术语
	 */
	public KeyWord getKeyWordByName(String name) {
		KeyWord keyWord = new KeyWord();
		keyWord = keyWordsMapCache.get(name);

		if (keyWord == null) {
			keyWord = getkeyWordFromDb(name);
		}
		return keyWord;
	}

	/**
	 * 根据符号名称查询数据库术语
	 * 
	 * @param name
	 * @return
	 */
	public KeyWord getkeyWordFromDb(String name) {
		KeyWord keyWord = new KeyWord();
		keyWord = keyWordServcie.selectKeyWordByName(name);// 从数据库查询
		if (keyWord != null) {
			keyWordsMapCache.put(name, keyWord);
		}
		return keyWord;
	}

	public Map<String, KeyWord> getKeyWordMapCache() {
		return this.keyWordsMapCache;
	}
}
