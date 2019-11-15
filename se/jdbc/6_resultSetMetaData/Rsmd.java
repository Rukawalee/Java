import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

public class Rsmd {
	public static void main(String[] args) {
		Rsmd r = new Rsmd();
		System.out.println(r.queryOne(Customer.class, "SELECT * FROM tb_customers WHERE id > ?", 1));
		r.printInfo("SELECT * FROM tb_customers");
	}
	
	public void printInfo(String sql, Object... args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < args.length; i++){
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			System.out.println("columnCount : " + columnCount);
			for(int i = 0; i < columnCount; i++){
				System.out.println("columnName : " + rsmd.getColumnName(i + 1));
				System.out.println("columnLabel : " + rsmd.getColumnLabel(i + 1));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps, rs);
		}
	}

	public <T> T queryOne(Class<T> clazz, String sql, Object... args) {
		T entity = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			Map<String, Object> map = new HashMap<String, Object>();
			if(rs.next()) {
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnLabel(i + 1), rs.getObject(i +1));
				}
			}
			if(!map.isEmpty()) {
				entity = clazz.getDeclaredConstructor().newInstance();
				Method[] methods = clazz.getDeclaredMethods();
				for(Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
					for(Method m : methods) {
						if(m.getName().equals(methodName)) {
							m.invoke(entity, value);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps, rs);
		}
		return entity;
	}
}