package rukawalee.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import rukawalee.jdbc.dao.DAO;

public class JDBCDAOImpl<T> implements DAO<T> {
	private QueryRunner qr;
	private Class<T> type;
	
	public JDBCDAOImpl(){
		qr = new QueryRunner();
		setType();
	}
	
	private void setType() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	public void update(Connection conn, String sql, Object... args) throws SQLException {
		
	}
	
	public <E> E queryValue(Connection conn, String sql, Object... args) throws SQLException {
		return null;
	}
	
	public T queryOne(Connection conn, String sql, Object... args) throws SQLException {
		return qr.query(conn, sql, new BeanHandler<T>(type), args);
	}
	
	public List<T> queryList(Connection conn, String sql, Object... args) throws SQLException {
		return null;
	}
	
	public void batch(Connection conn, String sql, Object[] ... args) throws SQLException {
		
	}
}