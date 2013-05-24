package programming.labs.lab1301threads_sol;

public class OverdraftException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OverdraftException(String msg) {
		super(msg);
	}
}
