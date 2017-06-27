package com.lantone.icss.web.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.KeyWord;
import com.lantone.icss.web.kl.cache.MedicineWordsCache;
import com.lantone.nlp.core.IKSegmenter;
import com.lantone.nlp.core.Lexeme;

/**
 * @author 吴文俊
 * @data 2016年12月3日 杭州朗通信息技术有限公司
 * @describe 分词工具类
 */
public class WordUtil {

	/**
	 * IK分词
	 * 
	 * @param word
	 * @return
	 */
	public List<String> iKAnalysis(String str) {
		List<String> ikStringList = Lists.newArrayList();// 分好词的数组

		try {
			byte[] bt = str.getBytes();
			InputStream ip = new ByteArrayInputStream(bt);
			Reader read = new InputStreamReader(ip);
			IKSegmenter iks = new IKSegmenter(read, true);// 加载字典
			Lexeme t;
			while ((t = iks.next()) != null) {
				ikStringList.add(t.getLexemeText());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ikStringList;

	}

	/**
	 * 英文转中文替换适配器
	 */
	public String englishToChineseUtil(String str, MedicineWordsCache medicineWordsCache) {

		List<KeyWord> keyWords = Lists.newArrayList();
		Map<String, KeyWord> medicineWordsMap = medicineWordsCache.getMedicineWordsMapCache();
		// 将Map中的值按长度从大到小排序
		for (String key : medicineWordsMap.keySet()) {

			keyWords.add(medicineWordsCache.getmedicineWordByName(key));
		}
		int size = keyWords.size();

		for (int out = size - 1; out > 1; out--) {
			for (int i = 0; i < out; i++) {
				if (keyWords.get(i).getName().length() > keyWords.get(i + 1).getName().length()) {
					keyWords.add(i, keyWords.get(i + 1));
					keyWords.remove(i + 2);
				}
			}
		}

		// 替换术语为中文
		for (int i = size - 1; i >= 0; i--) {
			str = str.replace(keyWords.get(i).getName(), keyWords.get(i).getMean());
		}

		return str;
	}

	/**
	 * 对输入的符号和英文单词统一转化处理
	 * 
	 * @param input
	 * @return
	 */
	public String regularize(String str) {
		// str =str.replaceAll("\\s*", "");//去空格

		char[] strChar = str.toCharArray();
		String returnString = "";
		for (int i = 0; i < strChar.length; i++) {
			char input = strChar[i];
			if ((input > 65280) && (input < 65375)) {
				input = (char) (input - 65248);
				returnString = returnString + String.valueOf(input);
			} else if ((input >= '0') && (input <= '9')) {
				if (i == 0) {
					returnString = returnString + "[" + String.valueOf(input);
					if (((strChar[(i + 1)] < '0') || (strChar[(i + 1)] > '9'))) {
						returnString = returnString + "]";
					}
				}
				if ((i > 0) && (i < strChar.length - 1)) {
					if ((strChar[(i - 1)] < '0') || (strChar[(i - 1)] > '9')) {
						returnString = returnString + "[" + String.valueOf(input);
					}
					if ((strChar[(i - 1)] >= '0') && (strChar[(i - 1)] <= '9') && (strChar[(i + 1)] >= '0') && (strChar[(i + 1)] <= '9')) {
						returnString = returnString + String.valueOf(input);
					}
					if (((strChar[(i + 1)] < '0') || (strChar[(i + 1)] > '9'))&&((strChar[(i - 1)] >= '0') &&(strChar[(i - 1)] <= '9'))) {
						returnString = returnString + String.valueOf(input) + "]";
					}
					if (((strChar[(i + 1)] < '0') || (strChar[(i + 1)] > '9'))&&((strChar[(i - 1)] < '0') || (strChar[(i - 1)] > '9'))) {
						returnString = returnString + "]";
					}
				}

				if ((i == strChar.length - 1) && (input >= '0') && (input <= '9'))
					returnString = returnString + String.valueOf(input) + "]";
			} else if ((input >= 'A') && (input <= 'Z')) {
				input = (char) (input + ' ');
				returnString = returnString + String.valueOf(input);
			} else {
				returnString = returnString + String.valueOf(input);
			}
		}
		return returnString;
	}

	/**
	 * 将字符串按符号划分
	 * 
	 * @param emr
	 * @return
	 */
	public List<String> emrSplit(String emr) {
		String[] kgGroup;
		String[] jhGroup;
		String[] fhGroup;
		String[] dhGroup;
		jhGroup = emr.split("。");// 按句号划分
		List<String> kgList = Lists.newArrayList();
		// 按空格划分
		for (String kgEmr : jhGroup) {
			kgGroup = kgEmr.split(" ");
			List<String> list = Arrays.asList(kgGroup);
			kgList.addAll(list);
		}
		String[] kgString = (String[]) kgList.toArray(new String[kgList.size()]);

		List<String> fhList = Lists.newArrayList();
		// 按分号划分
		for (String jhEmr : kgString) {
			fhGroup = jhEmr.split(";");
			List<String> list = Arrays.asList(fhGroup);
			fhList.addAll(list);
		}
		String[] fhString = (String[]) fhList.toArray(new String[fhList.size()]);
		// 按逗号划分
		List<String> dhList = Lists.newArrayList();
		for (String dhEmr : fhString) {
			dhGroup = dhEmr.split(",");
			List<String> list = Arrays.asList(dhGroup);
			dhList.addAll(list);
		}
		return dhList;
	}
	/**
	 * 主诉现病史划分
	 * @param chief
	 * @return
	 */
	public List<String> chiefSplit(String chief) {
		String[] banGroup;
		String[] jhGroup;
		jhGroup = chief.split("。");// 按句号划分
		List<String> banList = Lists.newArrayList();
		for (String banEmr : jhGroup) {
			banGroup = banEmr.split("伴");
			List<String> list = Arrays.asList(banGroup);
			banList.addAll(list);
		}
		return banList;
	}
	/**
	 *体征划分
	 */
	public List<String> signSplit(String sign) {
		String[] dhGroup;
		dhGroup = sign.split(",");// 按句号划分	
		List<String> list = Arrays.asList(dhGroup);		
		return list;
	}	
	/**
	 * 实验室检查划分
	 * @param chief
	 * @return
	 */
	public List<String> lisSplit(String lis) {
		String[] jhGroup;
		jhGroup = lis.split("。");// 按句号划分
		List<String> banList = Lists.newArrayList();
		return banList;
	}
	/**
	 * 影像学检查划分
	 * @param chief
	 * @return
	 */
	public List<String> pacsSplit(String pacs) {
		String[] jhGroup;
		jhGroup = pacs.split("。");// 按句号划分
		List<String> banList = Lists.newArrayList();
		return banList;
	}
	
}
