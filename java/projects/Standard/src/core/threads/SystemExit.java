package core.threads;

/**
 * This demo shows why System.exit is a bad idea (finally is never called). This
 * does not mean that System.exit should never be called - only that it should
 * be called with extreme care, and probably in less occasions that it is
 * currently being used in...
 * @author Mark Veltzer <mark@veltzer.net>
 */

public abstract class SystemExit {
	public static void main(String[] args) {
		try {
			System.out.println("in try block");
			System.exit(0);
			return;
		} finally {
			System.out.println("in finally block");
		}
	}
}
