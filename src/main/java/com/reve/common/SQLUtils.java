package com.reve.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLUtils {

	public static void bindParams(PreparedStatement st, List params) throws NumberFormatException, SQLException {
		if (null == params) {
			return;
		}
		for (int i = 0; i < params.size(); i++) {
			Object obj = params.get(i);
			int parameterIndex = i + 1;
			// number
			if (obj instanceof Integer) {
				st.setInt(parameterIndex, Integer.parseInt(StringUtils.number(obj)));
			}
			// double
			else if (obj instanceof Double) {
				st.setDouble(parameterIndex, Double.parseDouble(StringUtils.number(obj)));
			}
			// float
			else if (obj instanceof Float) {
				st.setFloat(parameterIndex, Float.parseFloat(StringUtils.number(obj)));
			}
			else {
				st.setString(parameterIndex, StringUtils.string(obj));
			}
		}
		
	}

}
