import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Transaction {
	public static void main(String[] args){
		Transaction t = new Transaction();
		Connection conn = JdbcUtil.getConnection();
		try {
			String sql = "UPDATE tb_users SET balance = balance + ? WHERE id = ?";
			// default value
			// conn.setAutoCommit(true);
			// begin transaction
			conn.setAutoCommit(false);
			t.update(conn, sql, +100, 1);
			// int i = 0 / 0;
			t.update(conn, sql, -100, 2);
			conn.commit();
			System.out.println("事务提交成功");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, null);
		}
	}
	
	public void update(Connection conn, String sql, Object... args){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(null, ps);
		}
	}
}