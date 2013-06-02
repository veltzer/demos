package programming.labs.lab1301threads_sol;

public abstract class Test {

	public static void main(String[] args) {
		AddCustomer add = new AddCustomer();
		RemoveCustomer remove = new RemoveCustomer();

		Thread add1 = new Thread(add);
		Thread add2 = new Thread(add);
		Thread remove1 = new Thread(remove);
		// Thread remove2 = new Thread(remove);

		add1.setName("adder1");
		add2.setName("adder2");
		remove1.setName("remover1");
		// remove2.setName("remover2");

		add1.start();
		add2.start();
		remove1.start();
		// remove2.start();
	}
}
