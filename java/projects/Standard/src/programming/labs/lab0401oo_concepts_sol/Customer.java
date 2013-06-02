package programming.labs.lab0401oo_concepts_sol;

public class Customer {
	private String name;
	private String id;
	private int age;

	public Customer() {
		this("John", "is-111", 30);
	}

	public Customer(String iname, String iid, int iage) {
		setName(iname);
		setId(iid);
		setAge(iage);
	}

	public int getAge() {
		return age;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAge(int iage) {
		if (iage > 0 && iage < 120) {
			age = iage;
		}
	}

	public void setId(String iid) {
		id = iid;
	}

	public void setName(String iname) {
		name = iname;
	}

}
