public class Test {

	public static void main(String[] args) {
		AddCustomer add=new AddCustomer();
		RemoveCustomer remove=new RemoveCustomer();
		
		Thread t1=new Thread(add);
		Thread t2=new Thread(add);
		Thread t3=new Thread(remove);
		Thread t4=new Thread(remove);
		
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		t4.setName("t4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
