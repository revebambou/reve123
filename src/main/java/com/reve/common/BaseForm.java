package com.reve.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BaseForm extends ActionForm implements Serializable {

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        return (errors);

    }
	
	private Integer id;
	private String username;
	private String password;
}
