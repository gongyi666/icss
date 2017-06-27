package com.lantone.test.provider;


import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws IOException {  
       ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
                new String[] { "spring-registry.xml", "spring-mybatis.xml" });  
        context.start();  
        System.in.read(); // 按任意键退出  
		
    } 
}

