package programming.labs.lab1301threads_sol;

public class AddCustomer implements Runnable {
	private Business bank;

	public AddCustomer() {
		bank = Bank.getBank();
	}

	public void run() {
		int i = 0;
		while (i <= 100) {
			int custnum;
			// Explain, for every line in the synchronized
			// block what is it doing inside the synchronized
			// block
			synchronized (bank) {
				// this adds a customer
				custnum = bank.getNumOfCustomers();
				if (custnum < 50) {
					bank.addCustomer(new Customer());
					custnum++;
				}
			}
			// Why did we move the println out of the
			// synchronized block ?
			System.out.println(Thread.currentThread().getName()
					+ ": Num of Customers " + custnum);
			i++;
			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
