import interbit.bank.*;
public class Test {

	public static void main(String[] args) {
		Business bank=Bank.getBank();
		bank.addCustomer(new Customer("Moshe","1234",20));
		
		try {
			Writer writer=new Writer();
			writer.write(bank);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bank=null;
		
		try {
			Reader reader=new Reader();
			bank=(Business)reader.read();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(bank.getCustomer(0).getName());
	}
}
