package programming.labs.lab1402io_sol;

public class OverdraftException extends Exception {
	private static final long serialVersionUID = 1L;

	public OverdraftException(String msg) {
		super(msg);
	}
}
