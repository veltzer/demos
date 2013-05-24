package programming.solutions.sol1301threads;

public class OverdraftException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OverdraftException(String msg) {
		super(msg);
	}
}
