import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.BeanUtils;

public class DaoDemo {
	public void update(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps);
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
					map.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
				}
			}
			if(!map.isEmpty()) {
				entity = clazz.getDeclaredConstructor().newInstance();
				Method[] methods = clazz.getDeclaredMethods();
				Map<String, Method> methodMap = new HashMap<String, Method>();
				for(Method m : methods) {
					methodMap.put(m.getName(), m);
				}
				for(Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
					methodMap.get(methodName).invoke(entity, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps, rs);
		}
		return entity;
	}

	public <T> List<T> queryList(Class<T> clazz, String sql, Object... args) {
		T entity = null;
		List<T> list = new ArrayList<T>();
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
			Method[] methods = clazz.getDeclaredMethods();
			Map<String, Method> methodMap = new HashMap<String, Method>();
			for(Method m : methods) {
				methodMap.put(m.getName(), m);
			}
			while(rs.next()) {
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
				}
				entity = clazz.getDeclaredConstructor().newInstance();
				for(Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
					methodMap.get(methodName).invoke(entity, value);
				}
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps, rs);
		}
		return list;
	}
	
	public <E> E queryValue(String sql, Object... args){
		E value = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				value = (E) rs.getObject(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps, rs);
		}
		return value;
	}
}