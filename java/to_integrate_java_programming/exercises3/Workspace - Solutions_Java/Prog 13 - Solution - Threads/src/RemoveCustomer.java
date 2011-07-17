import interbit.bank.*;
public class RemoveCustomer implements Runnable{
	Business bank;
	
	public RemoveCustomer(){
		bank=Bank.getBank();
	}

	public void run(){
		int i=0;
		while(i<=100){
			if(bank.getNumOfCustomers()>0)
				bank.removeCustomer(bank.getNumOfCustomers()-1);
			System.out.println(Thread.currentThread().getName()+
									" Num of Customers "+bank.getNumOfCustomers());
			i++;
			try {
				Thread.sleep((int)(Math.random()*500));
			} catch (Exception e) {

			}
		}
	}
}