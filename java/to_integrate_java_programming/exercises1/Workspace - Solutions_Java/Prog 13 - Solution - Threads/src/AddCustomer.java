import interbit.bank.*;
public class AddCustomer implements Runnable{
	Business bank;
	
	public AddCustomer(){
		bank=Bank.getBank();
	}

	public void run(){
		int i=0;
		while(i<=100){
			if(bank.getNumOfCustomers()<50)
				bank.addCustomer(new Customer());
			System.out.println(Thread.currentThread().getName()+
								": Num of Customers "+bank.getNumOfCustomers());
			i++;
			try {
				Thread.sleep((int)(Math.random()*500));
			} catch (Exception e) {

			}
		}
	}
}
