package com.reve.common;

import java.sql.Connection;

public class BaseBean {

	private BaseForm form;
	private Connection connection;

	
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
	
}
