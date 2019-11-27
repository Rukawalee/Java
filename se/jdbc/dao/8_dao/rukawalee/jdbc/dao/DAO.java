package rukawalee.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	void batch(Connection conn, String sql, Object[] ... args) throws SQLException;
	
	List<T> queryList(Connection conn, String sql, Object... args) throws SQLException;
	
	T queryOne(Connection conn, String sql, Object... args) throws SQLException;
	
	<E> E queryValue(Connection conn, String sql, Object... args) throws SQLException;
	
	void update(Connection conn, String sql, Object... args) throws SQLException;
	
}