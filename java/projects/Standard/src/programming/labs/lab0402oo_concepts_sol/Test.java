package programming.labs.lab0402oo_concepts_sol;

public abstract class Test {

	public static void main(String[] args) {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer("Vera", "is-112", 60);
		Customer customer3 = new Customer("Yossi", "is-112", 60, new Account(
				"8565/44", 35000));

		customer2.setAccount(customer3.getAccount());

		System.out.println("Accounts\n********");
		System.out.println("Customer1 name:" + customer1.getName() + " id:"
				+ customer1.getAccount().getId() + " balance:"
				+ customer1.getAccount().getBalance());
		System.out.println("Customer2 name:" + customer2.getName() + " id:"
				+ customer2.getAccount().getId() + " balance:"
				+ customer2.getAccount().getBalance());
		System.out.println("Customer3 name:" + customer3.getName() + " id:"
				+ customer3.getAccount().getId() + " balance:"
				+ customer3.getAccount().getBalance());
	}
}
