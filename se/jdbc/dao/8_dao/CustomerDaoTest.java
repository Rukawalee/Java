import java.sql.Connection;
import java.sql.SQLException;

import rukawalee.jdbc.utils.JDBCUtils;
import rukawalee.jdbc.dao.impl.CustomerDao;
import rukawalee.jdbc.domain.Customer;

public class CustomerDaoTest {
	private CustomerDao cd = new CustomerDao();
	
	public static void main(String[] args) {
		CustomerDaoTest cdt = new CustomerDaoTest();
		System.out.println("[Result] : " + cdt.queryOne("SELECT * FROM tb_customers WHERE id = ?", 2));
	}
	
	public void update(String sql, Object... args) {
		Connection conn = null;
	}
	
	public Customer queryOne(String sql, Object... args) {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			return cd.queryOne(conn, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, null, null);
		}
		return null;
	}
}