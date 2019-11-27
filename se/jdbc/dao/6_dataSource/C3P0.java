import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 1. import c3p0-0.9.5.4.jar
 * 2. import mchange-commons-java-0.2.19.jar
 **/
public class C3P0 {
	public static void main(String[] args) {
		C3P0 c = new C3P0();
		DataSource ds = null;
		try {
			ds = c.getDataSource();
			System.out.println("\r\n------split------");
			System.out.println("[c3p0 DataSource] : " + ds.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ds = c.getDataSourceByConfig();
			System.out.println("\r\n------split------");
			System.out.println("[c3p0 DataSource by config] : " + ds.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DataSource getDataSourceByConfig() throws SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource("mysql-config");
		return cpds;
	}
	
	public DataSource getDataSource() throws SQLException, PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql:///db_bookstore");
		cpds.setUser("root");
		cpds.setPassword("root");
		cpds.setInitialPoolSize(5);
		cpds.setMinPoolSize(5);
		cpds.setMaxPoolSize(10);
		return cpds;
	}
}