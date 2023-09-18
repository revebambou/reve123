package com.reve.common;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm implements Serializable {

	private Integer id;
	private String username;
	private String password;
}
