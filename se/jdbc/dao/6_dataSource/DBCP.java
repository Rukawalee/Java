import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * 1. import commons-dbcp2-2.7.0.jar
 * 2. import commons-pool2-2.7.0.jar
 **/
public class DBCP {
	public static void main(String... args) {
		DBCP dbcp = new DBCP();
		DataSource ds = dbcp.getDataSource();
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Ordinary DataSource : " + conn);
		ds = dbcp.getDataSourceByFactory();
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Factories' DataSource : " + conn);
	}
	
	public DataSource getDataSourceByFactory(){
		InputStream in = DBCP.class.getClassLoader().getResourceAsStream("dbcp.properties");
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public DataSource getDataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bds.setUrl("jdbc:mysql:///db_bookstore");
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setInitialSize(10);
		bds.setMaxIdle(20);
		bds.setMinIdle(10);
		return bds;
	}
}