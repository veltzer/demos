package extreme.threads;

/**
 * This demos that thread.interrupt
 * is only effective when the thread being interrupted is actualy in sleep, wait or
 * in a blocking io call (read/write). It is a signal delivered by
 * the OS.
 * 
 * @author Mark Veltzer
 */

public class InterruptDemo {
	// This thread runs forever
	private static class T1 extends Thread {

		@Override
		public void run() {
			try {
				float f=1;
				float result=0;
				while(true) {
					// This is to trick the smartass
					// compiler and his nagging warnings
					sleep(5);
					result+=Math.sin(f);
					f++;
				}
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	// This thread does sleeps
	private static class T2 extends Thread {
		
		@Override
		public void run() {
			while(true) {
				try {
					sleep(1);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		T1 t1=new T1();
		T2 t2=new T2();
		t1.start();
		t2.start();
		t1.interrupt();
		t2.interrupt();
	}
}
