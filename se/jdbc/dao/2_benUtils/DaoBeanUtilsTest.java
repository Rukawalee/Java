import java.util.List;

public class DaoBeanUtilsTest {
	DaoBeanUtils dbu = new DaoBeanUtils();
	
	public void testUpdate() {
		dbu.update("UPDATE tb_customers SET name = ? WHERE id = ?", 1, 2);
	}

	public void testQueryOne() {
		System.out.println(dbu.queryOne(Customer.class, "SELECT * FROM tb_customers WHERE id = ?", 3));
	}

	public void testQueryList() {
		List<Customer> list = dbu.queryList(Customer.class, "SELECT * FROM tb_customers");
		for(Customer c : list){
			System.out.println(c);
		}
	}
	
	public static void main(String[] args){
		DaoBeanUtilsTest dbut = new DaoBeanUtilsTest();
		dbut.testQueryOne();
		System.out.println("-----split-----");
		dbut.testUpdate();
		dbut.testQueryList();
	}
}