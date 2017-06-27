package com.lantone.icss.api.kl.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.KeyWord;
import com.lantone.icss.api.kl.model.wrapper.KeyWordWrapper;

public interface KeyWordServcie extends BaseService<KeyWord, KeyWordWrapper, Long> {
	/**
	 * 查询符号英文的医学词汇
	 * 
	 * @return
	 */
	List<KeyWord> selectMedicineWords();

	/**
	 * 根据名称查询符号英文的医学词汇
	 * 
	 * @return
	 */
	KeyWord selectMedicineWordByName(String name);

	/**
	 * 查询字典所有词
	 * 
	 * @return
	 */
	List<KeyWord> selectAllWords();

	/**
	 * 根据名称查询字典词库
	 * 
	 * @return
	 */
	KeyWord selectKeyWordByName(String name);

}
