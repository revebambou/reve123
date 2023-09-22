package com.reve.common;

import org.slf4j.Logger;

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
	
	public static void outputExceptionLog(Logger log, Exception e) {
		log.warn(e.getMessage() + e.getCause() + e.getClass());
	}
}
