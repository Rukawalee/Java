import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Rs {
	public void query(String sql) {
		//get Objects
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString(1) + ", " + rs.getObject(2) + ", " + rs.getString("email") + ", " + rs.getObject("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, stmt, rs);
		}
	}
	
	public void update(String sql){
		JdbcUtil.update(sql);
	}
	
	public void update(String sql, Object... args){
		JdbcUtil.update(sql, args);
	}

	public static void main(String... args) {
		Rs r = new Rs();
		r.update("UPDATE tb_customers SET name = 3 WHERE id = 2");
		r.update("UPDATE tb_customers SET name = ? WHERE id = ?", "张三", 3);
		r.query("SELECT * FROM tb_customers");
	}
}