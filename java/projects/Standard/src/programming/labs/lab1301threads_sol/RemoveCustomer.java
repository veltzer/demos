package programming.labs.lab1301threads_sol;

public class RemoveCustomer implements Runnable {
	private Business bank;

	public RemoveCustomer() {
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
				// this removes the last customer
				custnum = bank.getNumOfCustomers();
				if (custnum > 0) {
					bank.removeCustomer(custnum - 1);
					custnum--;
				}
			}
			// Why did we move the println out of the
			// synchronized block ?
			System.out.println(Thread.currentThread().getName()
					+ " Num of Customers " + custnum);
			i++;
			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
