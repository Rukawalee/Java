import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DBUTest{
	public static void main(String[] args) {
		DBU dbu = new DBU();
		String sql1 = "SELECT * FROM tb_customers";
		//System.out.println("\r\n" + dbu.query(sql1));
		System.out.println("[BeanHandler] : " + dbu.query(sql1, new BeanHandler(Customer.class)));
		String sql2 = "DELETE FROM tb_customers WHERE id = ?";
		System.out.println("\r\n------split------");
		System.out.println("[column changes] : " + dbu.update(sql2, 4));
		System.out.println("\r\n------split------");
		//System.out.println(dbu.query(sql1));
		System.out.println("[BeanListHandler] : " + dbu.query(sql1, new BeanListHandler(Customer.class)));
		System.out.println("\r\n------split------");
		System.out.println("[MapHandler] : " + dbu.query(sql1, new MapHandler()));
		System.out.println("\r\n------split------");
		System.out.println("[MapListHandler] : " + dbu.query(sql1, new MapListHandler()));		
		System.out.println("\r\n------split------");
		System.out.println("[ScalarHandler] : " + dbu.query(sql1, new ScalarHandler()));
	}
}