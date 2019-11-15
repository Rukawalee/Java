import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class Dmd{
	public static void main(String[] args){
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			DatabaseMetaData dmd = conn.getMetaData();
			int version = dmd.getDatabaseMajorVersion();
			System.out.println("version : " + version);
			String username = dmd.getUserName();
			System.out.println("username : " + username);
			rs = dmd.getCatalogs();
			System.out.println("Infomation for databases");
			while(rs.next()){
				System.out.println(rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, null, rs);
		}
	}
}