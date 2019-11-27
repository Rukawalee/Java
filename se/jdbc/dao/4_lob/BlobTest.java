import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class BlobTest{
	public static void main(String[] args) {
		BlobTest b = new BlobTest();
		int id = b.insertBlob("INSERT INTO tb_customers (name, email, birth, picture) VALUES (?, ?, ?, ?)");
		System.out.println("-----split-----");
		b.queryBlob("SELECT * FROM tb_customers WHERE id = ?", id);
	}
	
	public void queryBlob(String sql, Object arg){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStream in = null;
		OutputStream out = null;
		String path = System.getProperty("user.dir");
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, arg);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getObject(1) + ", " + rs.getObject(2) + ", " + rs.getObject(3) + ", " + rs.getObject(4));
				java.sql.Blob blob = rs.getBlob(5);
				in = blob.getBinaryStream();
				out = new FileOutputStream(path.substring(0, path.lastIndexOf("\\")) + "\\4_lob\\blobPic.ico");
				int len = 0;
				byte[] buffer = new byte[1024];
				while((len = in.read(buffer)) != -1){
					out.write(buffer, 0, len);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null){
					out.close();
				}
				out = null;
				if(in != null){
					in.close();
				}
				in = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			JdbcUtil.release(conn, ps, rs);
		}
	}
	
	public int insertBlob(String sql){
		Connection conn = null;
		PreparedStatement ps = null;
		InputStream in = null;
		ResultSet rs = null;
		String path = System.getProperty("user.dir");
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "wangMaZi");
			ps.setString(2, "wangMaZi@rukawalee.com");
			ps.setDate(3, new Date(new java.util.Date().getTime()));
			in = new FileInputStream(path.substring(0, path.lastIndexOf("\\")) + "\\4_lob\\Run.ico");
			ps.setBlob(4, in);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null){
					in.close();
				}
				in = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			JdbcUtil.release(conn, ps);
		}
		return -1;
	}
}