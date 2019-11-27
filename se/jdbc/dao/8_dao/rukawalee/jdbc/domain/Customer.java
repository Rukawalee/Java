package rukawalee.jdbc.domain;

import java.util.Date;

public class Customer {
	private int id;
	private String name;
	private String email;
	private Date birth;
	
	public Customer() {}
	
	public Customer(int id, String name, String email, Date birth) {
		setId(id);
		setName(name);
		setEmail(email);
		setBirth(birth);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setBirth(Date birth){
		this.birth = birth;
	}
	
	public Date getBirth(){
		return birth;
	}
	
	public String toString(){
		return "Customer [id = '" + id + "', name = '" + name + "', email = '" + email + "', birth = '" + birth +"']\r\n";
	}
}