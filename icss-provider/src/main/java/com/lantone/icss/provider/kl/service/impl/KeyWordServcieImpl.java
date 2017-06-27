package com.lantone.icss.provider.kl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.KeyWord;
import com.lantone.icss.api.kl.model.wrapper.KeyWordWrapper;
import com.lantone.icss.api.kl.service.KeyWordServcie;
import com.lantone.icss.provider.kl.dao.KeyWordMapper;

@Service
public class KeyWordServcieImpl extends BaseServiceImpl<KeyWord, KeyWordWrapper, Long> implements KeyWordServcie {
	@Autowired
	private KeyWordMapper keyWordMapper;

	@Override
	public List<KeyWord> selectMedicineWords() {
		return keyWordMapper.selectMedicineWords();
	}

	@Override
	public KeyWord selectMedicineWordByName(String name) {
		return keyWordMapper.selectMedicineWordByName(name);
	}

	@Override
	public List<KeyWord> selectAllWords() {
		return keyWordMapper.selectAllWords();
	}

	@Override
	public KeyWord selectKeyWordByName(String name) {
		// TODO Auto-generated method stub
		return keyWordMapper.selectKeyWordByName(name);
	}

}
