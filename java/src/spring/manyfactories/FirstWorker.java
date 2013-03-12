package spring.manyfactories;

public class FirstWorker implements IWorker {

	@Override
	public void doWork() {
		System.out.println("first worker working");
	}

}
