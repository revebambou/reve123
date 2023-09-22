package com.reve.common;

import java.sql.Connection;

import org.slf4j.Logger;

public class BaseBean {

	private BaseForm form;
	private Connection connection;
	protected Logger log;

	
	public void testConnection() {
		
	}
	
	public void testSync() {
		
	}
	
	/**
	 * @return the form
	 */
	public BaseForm getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(BaseForm form) {
		this.form = form;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Logger log) {
		this.log = log;
	}
	
}
