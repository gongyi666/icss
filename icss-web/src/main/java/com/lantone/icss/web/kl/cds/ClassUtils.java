/**
 * The cdsframework support client aims at making vMR generation easier.
 *
 * Copyright 2013 HLN Consulting, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information about the this software, see https://cdsframework.atlassian.net/wiki or send
 * correspondence to scm@cdsframework.org.
 */
package com.lantone.icss.web.kl.cds;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HLN Consulting, LLC
 */
public class ClassUtils {

	/**
	 * 杩斿洖绫绘墍鍦ㄧ殑鍖呯殑浣嶇疆
	 *
	 * @param 绫
	 *            �
	 * @return 鎵�鍦ㄧ殑鍖�
	 */
	public static String getClassPackageName(Class<?> klass) {
		String result = null;
		if (klass == null) {
			throw new IllegalArgumentException("The class cannot be null.");
		}
		String canonicalName = klass.getCanonicalName();
		result = canonicalName.substring(0, canonicalName.lastIndexOf("."));
		return result;
	}

	/**
	 * Returns all subclass
	 * 
	 * @param cls
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> getAllAssignedClass(Class<?> cls) throws IOException, ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c : getClasses(cls)) {
			if (cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}
		return classes;
	}

	/*
	 * 杩斿洖璇ヨ矾寰勪笅鐨勬墍鏈夌被
	 */
	public static List<Class<?>> getClasses(Class<?> cls) throws IOException, ClassNotFoundException {
		String pk = cls.getPackage().getName();
		String path = pk.replace('.', '/');
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource(path);
		return getClasses(new File(url.getFile()), pk);
	}

	private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!dir.exists()) {
			return classes;
		}
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				classes.addAll(getClasses(f, pk + "." + f.getName()));
			}
			String name = f.getName();
			if (name.endsWith(".class")) {
				classes.add(Class.forName(pk + "." + name.substring(0, name.length() - 6)));
			}
		}
		return classes;
	}

}
