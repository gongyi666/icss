package com.lantone.icss.provider.kl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.KeyWord;
import com.lantone.icss.api.kl.model.wrapper.KeyWordWrapper;

public interface KeyWordMapper extends EntityMapper<KeyWord, KeyWordWrapper, Long> {
	/**
	 * 查询英文符号的医学术语
	 * 
	 * @return
	 */
	List<KeyWord> selectMedicineWords();

	KeyWord selectMedicineWordByName(@Param("name") String name);

	/**
	 * 查询词库所有的词
	 * 
	 * @return
	 */
	List<KeyWord> selectAllWords();

	/**
	 * 查询词库所有的词
	 * 
	 * @param name
	 * @return
	 */
	KeyWord selectKeyWordByName(@Param("name") String name);
}