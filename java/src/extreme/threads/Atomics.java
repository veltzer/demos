package extreme.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A demonstration of how to use atomic variables.
 *
 * @author Mark Veltzer
 */

public class Atomics {
	
	private static class MyRunnable implements Runnable {
		private int myVal;
		private AtomicInteger at;
		public MyRunnable(int initval,AtomicInteger iat) {
			myVal=initval;
			at=iat;
		}
		public void run() {
			while(true) {
				if(at.compareAndSet(myVal, myVal+1)) {
					myVal++;
				}
				if(myVal%5000==0) {
					System.out.println("myVal is "+myVal);
				}
			}
		}
	}
	
	static public void main(String[] args) {
		AtomicInteger at=new AtomicInteger(0);
		Runnable run1=new MyRunnable(0, at);
		Runnable run2=new MyRunnable(1, at);
		new Thread(run1);
		new Thread(run2);
	}
}