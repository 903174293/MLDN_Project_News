package cn.mldn.zwb.dao.abs;

import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.mldn.zwb.dbc.DatabaseConnection;

public abstract class AbstractDAO {

	protected PreparedStatement pstmt;
	protected Connection conn;

	public AbstractDAO() {
		// 以下这一步很关键：巧妙的减少了重复的代码
		this.conn = DatabaseConnection.getConnection();

	}
}
