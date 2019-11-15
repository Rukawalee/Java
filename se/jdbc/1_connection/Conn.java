import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class Conn {
	//定义数据库加载四大参数
	private static String driverClass;
	private static String jdbcUrl;
	private static String user;
	private static String password;

	static {
		//定义配置文件输入流
		InputStream in = Conn.class.getClassLoader().getResourceAsStream("jdbc.properties");
		//定义配置加载类加载输入流
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driverClass = props.getProperty("driverClass");
		jdbcUrl = props.getProperty("jdbcUrl");
		user = props.getProperty("user");
		password = props.getProperty("password");
	}

	public Connection getConnection() {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			return DriverManager.getConnection(jdbcUrl, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection2() {
		Driver driver = null;
		try {
			driver = (Driver) Class.forName(driverClass).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Properties info = new Properties();
			info.put("user", user);
			info.put("password", password);
			return driver.connect(jdbcUrl, info);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String... args) {
		Conn conn = new Conn();
		System.out.println(conn.getConnection());
		System.out.println(conn.getConnection2());
	}
}