package extreme.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple blocking queue.
 * 
 * @author Mark Veltzer
 */

public class Atomics {
	
	private class MyRunnable implements Runnable {
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
			
			}
			
		}
	}
	
	static public void main(String[] args) {
		AtomicInteger at=new AtomicInteger(0);
		Runnable run=new Runnable() {
			@Override
			public void run() {				
			}
		};
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
			}
		})
	}
}
