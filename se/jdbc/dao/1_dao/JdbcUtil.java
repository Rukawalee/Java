import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class JdbcUtil {
	public static Connection getConnection() {
		InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String driverClass = props.getProperty("driverClass");
		String jdbcUrl = props.getProperty("jdbcUrl");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		try {
			Class.forName(driverClass);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			return DriverManager.getConnection(jdbcUrl, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void update(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String sql, Object... args) {
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
        }
    }

	public static void release(Connection conn, Statement stmt) {
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

	public static void release(Connection conn, Statement stmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		release(conn, stmt);
	}
}