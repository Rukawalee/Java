import java.util.List;

public class DaoTest{
	DaoDemo dd = new DaoDemo();
	
	public void testUpdate(){
		dd.update("INSERT INTO tb_customers (name, email, birth) VALUES (?, ?, ?)", "zhaoLiu", "zhaoLiu@rukawalee.com", "2000-12-12");
		System.out.println("Success");
	}
	
	public void testQueryOne(){
		System.out.println(dd.queryOne(Customer.class, "SELECT * FROM tb_customers WHERE id = ?", 3));
	}
	
	public void testQueryList(){
		List<Customer> list = dd.queryList(Customer.class, "SELECT * FROM tb_customers");
		for(Customer c : list){
			System.out.println(c);
		}
	}
	
	public void testQueryValue(){
		Object value = dd.queryValue("SELECT COUNT(*) FROM tb_customers");
		System.out.println(value);
	}
	
	public static void main(String[] args){
		DaoTest dt = new DaoTest();
		dt.testUpdate();
		System.out.println("--------");
		dt.testQueryOne();
		System.out.println("--------");
		dt.testQueryList();
		System.out.println("--------");
		dt.testQueryValue();
	}
}