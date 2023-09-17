package com.reve.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtils {
	private static Connection conn;

	// SQL-URL(jdbc:mysql://ip:3306/数据库名)
	// SQL-URL: jdbc:postgresql://ip:5432/database name
	public static final String SQL_URL = "jdbc:postgresql://10.211.55.5:5432/reve";
    // 登录的用户名
	public static final String SQL_USER  = "postgres";
    // 登录密码
	public static final String SQL_PASSWD = "postgres";
	// 登录密码
	public static final String SQL_DRIVER = "org.postgresql.Driver";

	static {
		try {
			// 加载PostgreSQL驱动
	        Class.forName(SQL_DRIVER);
			conn = DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASSWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
//       	// 加载PostgreSQL驱动
//        Class.forName(SQL_DRIVER);
//       // 创建连接
//        Connection conn = DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASSWD);

        return conn;
	}
	
	/**
	 * close data source
	 * @param rs
	 * @param st
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException {
		close(rs, st);
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * close data source
	 * @param rs
	 * @param st
	 * @throws SQLException
	 */
	public static void close(ResultSet rs, PreparedStatement st) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
	}
}
