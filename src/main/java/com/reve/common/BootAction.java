package com.reve.common;

import java.lang.reflect.Constructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reve.common.BaseAction;
import com.reve.common.BaseBean;
import com.reve.common.BaseForm;
import com.reve.common.Constant;

public class BootAction extends BaseAction {

	protected String doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BaseForm baseForm = (BaseForm) form;
		String formName = form.getClass().getName();
//		formName = form.getClass().getSimpleName();
		String beanName = formName.substring(0, formName.length() - 4) + "Bean";
		Class cls = Class.forName(beanName);
		Constructor constructor = cls.getConstructor(null);
		BootBean baseBean = (BootBean) constructor.newInstance(null);
		baseBean.setForm(baseForm);
		baseBean.testSync();
		baseBean.setLog(log);
		baseBean.manjyuBoot();
		
		// TODO Auto-generated method stub
		return Constant.FORWARD_SUCCESS;
	}
}
