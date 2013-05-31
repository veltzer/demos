package programming.labs.lab0702inheritance_sol;

public abstract class Test {

	public static void main(String[] args) {
		Bank bank = Bank.getBank();
		for (int i = 0; i < 3; i++) {
			double rand = 13000 * Math.random();
			int randi = (int) rand;
			double randd = (double) randi;
			Account a = new Account(randd);
			Customer c = new Customer("cust" + i, "id" + i, 18 + i, a);
			bank.addCustomer(c);
		}
		System.out.println(bank);
		bank.getCustomer(0).getAccount(0).withdraw(6000);
		bank.getCustomer(1).getAccount(0).withdraw(6000);
		bank.getCustomer(2).getAccount(0).withdraw(6000);
		System.out.println(bank);
	}
}
