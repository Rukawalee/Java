import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ps {
	public static void main(String... args){
		Ps p = new Ps();
		p.query("SELECT * FROM tb_customers");
		p.update("UPDATE tb_customers SET name = ? WHERE id = ?", "SSM", 2);
		System.out.println("---------split----------");
		p.query("SELECT * FROM tb_customers");
		System.out.println("---------split----------");
		System.out.println(p.getGeneratedKey("INSERT INTO tb_customers (name, email, birth) VALUES('laoQi', 'laoQi@rukawalee.com', '2012-12-12')"));
	}
	
	public Object getGeneratedKey(String sql){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object key = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				key = rs.getObject(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps, rs);
		}
		return key;
	}
	
	public void query(String sql, Object... args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < args.length; i++){
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getObject(1) + ", " + rs.getString(2) + ", " + rs.getObject("email") + ", " + rs.getString("birth"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			JdbcUtil.release(conn, ps, rs);
		}
	}
	
	public void update(String sql, Object... args){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < args.length; i++){
				ps.setObject(i + 1, args[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.release(conn, ps);
		}
	}
}