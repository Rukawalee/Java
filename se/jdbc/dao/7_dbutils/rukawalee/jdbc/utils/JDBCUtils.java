package rukawalee.jdbc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 1. import c3p0-0.9.5.4.jar
 * 2. import mchange-commons-java-0.2.19.jar
 **/
public class JDBCUtils {
	private static DataSource dataSource;

	static {
		dataSource = new ComboPooledDataSource("mysql-config");
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void openTransaction(Connection conn) throws SQLException {
		if(conn != null) {
			conn.setAutoCommit(false);
		}
	}

	public static void commitTransaction(Connection conn) throws SQLException {
		if(conn != null) {
			conn.commit();
		}
	}

	public static void rollbackTransaxtion(Connection conn) {
		if(conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void release(Connection conn, Statement stmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}