import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 1. import commons-beanutils-1.9.4.jar
 * 2. import commons-logging-1.2.jar
 * 3. import commons-collections-3.2.2.jar
 **/
public class DaoBeanUtils {
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
		List<T> list = queryList(clazz, sql, args);
		T entity = null;
		if(!list.isEmpty()){
			entity = list.get(0);
		}
		return entity;
	}

	public <T> List<T> queryList(Class<T> clazz, String sql, Object... args) {
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
			List<Map<String, Object>> records = getRecordList(rs);
			list = transferToObjectList(clazz, records);
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
	
	public <T> List<T> transferToObjectList(Class<T> clazz, List<Map<String, Object>> records) throws Exception {
		T entity = null;
		List<T> list = new ArrayList<T>();
		for(Map<String, Object> r : records) {
			entity = clazz.getDeclaredConstructor().newInstance();
			for(Map.Entry<String, Object> entry : r.entrySet()) {
				BeanUtils.setProperty(entity, entry.getKey(), entry.getValue());
			}
			list.add(entity);
		}
		return list;
	}

	public List<Map<String, Object>> getRecordList(ResultSet rs) throws SQLException {
		List<String> labels = getColumnLabels(rs);
		Map<String, Object> record = null;
		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		while(rs.next()) {
			record = new HashMap<String, Object>();
			for(String label : labels) {
				record.put(label, rs.getObject(label));
			}
			records.add(record);
		}
		return records;
	}
	
	public List<String> getColumnLabels(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd = rs.getMetaData();
		List<String> labels = new ArrayList<String>();
		for(int i = 0; i < rsmd.getColumnCount(); i++){
			labels.add(rsmd.getColumnLabel(i + 1));
		}
		return labels;
	}
}