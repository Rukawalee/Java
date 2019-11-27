package rukawalee.jdbc.utils;

import java.sql.SQLException;

public class JDBCUtilsTest {
	public static void main(String[] args) {
		try {
			System.out.println("[dataSource result] " + JDBCUtils.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}