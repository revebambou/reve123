package com.reve.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BaseAction extends Action {
	private static final String Session_User = "SessoinUser";
	private Logger log;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
		log = LoggerFactory.getLogger(getClass());
		log.debug(form.getClass().getSimpleName() + " is started.");
		log.info(form.getClass().getSimpleName() + " is started.");
        super.execute(mapping, form, request, response);
        
        //用户如果未登录或session过期，则转向登录动作。
        if (!isLogin(request))
            return mapping.findForward("login");

        try
        {
            String forward = doExecute(mapping, form, request, response);
            verify(null, "", "");
            //重新生成token
            //super.saveToken(request);
            log.info(form.getClass().getSimpleName() + " is ended.");
            return mapping.findForward(forward);
        } catch (Exception e)
        {
            //logger.error(e);
            e.printStackTrace();
            
            return mapping.findForward("systemError");
        }
    }

	protected boolean verify(Connection conn, String username, String passwd) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
		try {
            List params = new ArrayList();
            //查找和数据库存储数据一致的用户名
            String sql = "select password from reve.users where username = ? ";
            params.add(username);
            // 通过数据库的连接操作数据库，实现增删改查
            st = ConnectionUtils.getConnection().prepareStatement(sql);

            SQLUtils.bindParams(st, params);
            rs = st.executeQuery();

            //判断输入的密码是否和数据库中存储的密码是一致的
            while (rs.next()) {
                if(rs.getString("password").equals(passwd))
                    return true;
            }
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理驱动程序错误
            e.printStackTrace();
        } finally {
        	// 完成后关闭创建的实例
        	ConnectionUtils.close(rs, st);
        }
        return false;
    }

	protected String doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return Constant.FORWARD_SUCCESS;
	}

	/**
     * 判断用户是否已经登录。
     * 
     * @param request
     * @return
     */
    protected boolean isLogin(HttpServletRequest request) {
    	// TODO
    	request.getSession().setAttribute(Session_User, "");
        return request.getSession().getAttribute(Session_User) != null;
    }
}
