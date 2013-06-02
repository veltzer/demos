package spring.scheduling;

public class JustSomeClass {
	private String name;

	public void setName(String iname) {
		name = iname;
	}

	public void doIt() {
		System.out.println(name
				+ " being called directly, not through interface");
	}

}
