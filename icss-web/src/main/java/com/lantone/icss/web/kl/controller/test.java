package com.lantone.icss.web.kl.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.lantone.icss.web.common.util.WordUtil;
import com.lantone.nlp.core.IKSegmenter;
import com.lantone.nlp.core.Lexeme;



public class test {
	public static void main(String[] args) {
		String str = "T38°C  P65次/min  R77次/min  Bp80/120mmHg  发育异常，营养中等，神志清楚，面容烦躁，卧位，查体合作。全身皮肤及黏膜异常，见大腿部位有皮疹，颜色正常。指甲有反甲，有勺状甲，全身淋巴结未及明显肿大。瞳孔扩大，不等大，等圆，对光反射灵敏。患者平素体健，无“肝炎”、“结核”等传染病史。无手术史。";
		WordUtil wordUtil = new WordUtil();
		str=wordUtil.regularize(str);
		System.out.println(str);
		IKAnalysis(str);
	}

	public static String IKAnalysis(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			// InputStream in = new FileInputStream(str);//
			byte[] bt = str.getBytes();// str
			InputStream ip = new ByteArrayInputStream(bt);
			Reader read = new InputStreamReader(ip);
			IKSegmenter iks = new IKSegmenter(read, true);
			Lexeme t;

			while ((t = iks.next()) != null) {
				sb.append(t.getLexemeText() + "|");

			}
			sb.delete(sb.length() - 1, sb.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();

	}
}
