package com.reve.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Message {
	private static Logger log = LoggerFactory.getLogger("Message");
	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("/MessageResources");
	}

	public static String getMessage(String key) {
		String message;
		try {
			message = bundle.getString(key);
		} catch (MissingResourceException e) {
			log.warn(e.getMessage() + e.getCause() + e.getClass());
			message = "";
		}

		return message;
	}
}
