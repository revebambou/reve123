package com.reve.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.reve.common.BaseAction;

public class SampleAction extends BaseAction {
	private static final String Session_User = "SessoinUser";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        super.execute(mapping, form, request, response);

        //用户如果未登录或session过期，则转向登录动作。
        if (!isLogin(request))
            return mapping.findForward("login");

        try
        {
            String forward = doExecute(mapping, form, request, response);
            verify("", "");
            //重新生成token
            //super.saveToken(request);
            return mapping.findForward(forward);
        } catch (Exception e)
        {
            //logger.error(e);
            e.printStackTrace();
            
            return mapping.findForward("systemError");
        }
    }
	
	private boolean verify(String username, String passwd) {
        try {
        	// 加载PostgreSQL驱动
            Class.forName("org.postgresql.Driver");
            // 创建连接
            Connection conn = DriverManager.getConnection("jdbc:postgresql://10.211.55.5:5432/reve", "postgres", "postgres");
            // 通过数据库的连接操作数据库，实现增删改查
            Statement stmt = conn.createStatement();
            //查找和数据库存储数据一致的用户名
            String sql = "select password from reve.users where username ='"+username+"'";

            ResultSet rs = stmt.executeQuery(sql);

            //判断输入的密码是否和数据库中存储的密码是一致的
            while (rs.next()) {
                if(rs.getString("password").equals(passwd))
                    return true;
            }
            // 完成后关闭创建的实例
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理驱动程序错误
            e.printStackTrace();
        }
        return false;
    }

//	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		return mapping.findForward("success");
//	}

	/**
     * 判断用户是否已经登录。
     * 
     * @param request
     * @return
     */
    protected boolean isLogin(HttpServletRequest request)
    {
    	// TODO
    	request.getSession().setAttribute(Session_User, "");
        return request.getSession().getAttribute(Session_User) != null;
    }
}
