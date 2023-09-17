package com.reve.common;

public class StringUtils {

	public static String number(Object obj) {
		if (null == obj) {
			return "0";
		}
		
		return obj.toString();
	}

	public static String string(Object obj) {
		if (null == obj) {
			return "";
		}
		
		return obj.toString();
	}
}
