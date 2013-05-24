package interbit.bookstore;

public class Customer {
	private String id;
	private String name;
	private String email;
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	Customer()
	{
		
	}
	public Customer(String name, String email, String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
	}
	public Customer(String id, String name, String email, String address) {
		this(name, email, address);
		this.id = id;
	}
	@Override
	public String toString() {
		return String.format("Customer: id=%s, name=%s, email=%s, address=%s",
				id, name, email, address);
	}
}
