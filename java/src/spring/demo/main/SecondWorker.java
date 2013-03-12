package spring.demo.main;

public class SecondWorker implements IWorker {

	@Override
	public void doWork() {
		System.out.println("second worker working");
	}

}
