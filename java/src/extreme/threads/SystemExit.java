package extreme.threads;

/**
 * This demo shows why System.exit is a bad idea (finally is never called).
 * 
 * @author Mark Veltzer
 */

public class SystemExit {
	static public void main(String[] args) {
		try {
			System.out.println("in try block");
			System.exit(0);
			return;
		}
		finally {
			System.out.println("in finally block");
		}
	}
}