public class Customer {
	private String name;
	private String id;
	private int age;
	
	public Customer(){
		this("John","is-111",30);
	}
	
	public Customer(String name,String id,int age){
		setName(name);
		setId(id);
		setAge(age);
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

	public void setAge(int age) {
		if(age>0 && age<120)
			this.age=age;
	}

	public void setId(String id) {
		this.id=id;
	}

	public void setName(String name) {
		this.name=name;
	}

}
