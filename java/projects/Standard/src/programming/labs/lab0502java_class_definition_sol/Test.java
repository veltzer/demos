package programming.labs.lab0502java_class_definition_sol;

public abstract class Test {

	public static void main(String[] args) {
		Bank bank1 = Bank.getBank();

		bank1.addCustomer(new Customer("Yossi", "111", 41, new Account(4000)));
		bank1.addCustomer(new Customer("Pasi", "222", 42, new Account(5000)));
		bank1.addCustomer(new Customer("Moshe", "333", 43, new Account(6000)));

		Bank bank2 = Bank.getBank();
		System.out.println("Customer1 name:" + bank2.getCustomer(0).getName());
		System.out.println("Customer2 name:" + bank2.getCustomer(1).getName());
		System.out.println("Customer3 name:" + bank2.getCustomer(2).getName());
	}
}
