package org.meta.spring.demo.main;

public class FirstWorker implements IWorker {

	@Override
	public void doWork() {
		System.out.println("first worker working");
	}

}
