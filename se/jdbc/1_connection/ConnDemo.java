import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnDemo {
	/**
	 * DriverManager优势：
	 * 1. 通过getConnection()获取数据库连接比较方便
	 * 2. 可以同时管理多个数据库驱动程序
	 **/
	public Connection getConnection() {
		//1. 准备数据库连接四大参数
		//  1). 创建Properties对象
		Properties props = new Properties();
		//  2). 获取类路径下jdbc.properties输入流
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		//  3). 加载输入流
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//  4). 初始化数据库连接四大参数
		String driverClass = props.getProperty("driverClass");
		String jdbcUrl = props.getProperty("jdbcUrl");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		//2. 加载数据库连接驱动 （通过加载类中静态方法注册驱动）
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//3. 获取数据库连接
		try {
			return DriverManager.getConnection(jdbcUrl, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() {
		//1. 准备数据库连接四大参数
		//  1). 创建properties对象
		Properties props = new Properties();
		//  2). 获取类路径下jdbc.properties输入流
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		//  3). 加载输入流
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//  4). 初始化数据库连接四大参数
		String driverClass = props.getProperty("driverClass");
		String jdbcUrl = props.getProperty("jdbcUrl");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		//2. 获取Driver对象
		Driver driver = null;
		try {
			driver = (Driver) Class.forName(driverClass).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//3. 获取数据库连接
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
		ConnDemo cd = new ConnDemo();
		System.out.println(cd.getConnection());
		System.out.println(cd.getConnection2());
	}
}