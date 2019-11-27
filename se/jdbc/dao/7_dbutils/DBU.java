import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import rukawalee.jdbc.utils.JDBCUtils;

/**
 * 1. import commons-dbutils-1.7.jar
 **/
public class DBU {
	QueryRunner qr = new QueryRunner();
	
	class MyResultSetHandler implements ResultSetHandler {
		public Object handle (ResultSet rs) throws SQLException {
			List<Customer> customers = new ArrayList<Customer>();
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				Date birth = rs.getDate(4);
				customers.add(new Customer(id, name, email, birth));
			}
			return customers;
		}
	}
	
	public Object query(String sql, ResultSetHandler rsh, Object... args) {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			return qr.query(conn, sql, rsh);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, null, null);
		}
		return null;
	}
	
	/**
	 * For SELECT
	 **/
	public Object query(String sql, Object... args) {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			return qr.query(conn, sql, new MyResultSetHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, null, null);
		}
		return null;
	}
	
	/**
	 * For INSERT, UPDATE, DELETE
	 **/
	public int update(String sql, Object... args) {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			return qr.update(conn, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, null, null);
		}
		return -1;
	}
}