package com.nsmjsf.web.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.text.WordUtils;

@ManagedBean
public class Utils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3873866726611911336L;

	public static void printDebug(String tag, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(tag);
		sb.append(",");
		sb.append(message);

		System.out.println(sb.toString());

	}

	public static String randomFileName() {
		Long timestamp = (System.currentTimeMillis() / 1000L);
		String filename = RandomStringUtils.randomAlphanumeric(5)
				+ timestamp.toString();
		return filename;
	}

	public static <T> List<T> listFromSet(java.util.Set set) {
		List<T> list = new ArrayList<T>();
		list.addAll(set);
		return list;
	}

	public static HashMap<String, Object> getParameterMap() {
		return new HashMap<String, Object>();
	}

	public String toTitleCase(String sentence) {
		return WordUtils.capitalize(sentence);
	}

	public String beautifyString(String sentence, int width) {
		return WordUtils.wrap(WordUtils.capitalize(sentence), width);
	}

	public static <T> int safeListSize(List<T> list) {
		if (list != null) {
			return list.size();
		} else {
			return 0;
		}
	}

}
