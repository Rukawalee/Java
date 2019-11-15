import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Stmt {
	// For parameters for connection
	private static String driverClass;
	private static String jdbcUrl;
	private static String user;
	private static String password;

	// Get connection
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driverClass);
		return DriverManager.getConnection(jdbcUrl, user, password);
	}

	// Method for INSERT, UPDATE, DELETE
	public void update(String sql) {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}

	public static void main(String... args) {
		Stmt s = new Stmt();
		s.update("UPDATE tb_customers SET name = 'Rukawa' WHERE id = 2");
		System.out.println("finish");
	}

	static {
		InputStream in = Stmt.class.getClassLoader().getResourceAsStream("jdbc.properties");
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
}