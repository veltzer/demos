package meta.bookstore;

public class Customer {
	private String id;
	private String name;
	private String email;
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String iaddress) {
		address = iaddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String iemail) {
		email = iemail;
	}

	public String getId() {
		return id;
	}

	public void setId(String iid) {
		id = iid;
	}

	public String getName() {
		return name;
	}

	public void setName(String iname) {
		name = iname;
	}

	Customer() {

	}

	public Customer(String iname, String iemail, String iaddress) {
		super();
		name = iname;
		email = iemail;
		address = iaddress;
	}

	public Customer(String iid, String iname, String iemail, String iaddress) {
		this(iname, iemail, iaddress);
		id = iid;
	}

	@Override
	public String toString() {
		return String.format("Customer: id=%s, name=%s, email=%s, address=%s",
				id, name, email, address);
	}
}
