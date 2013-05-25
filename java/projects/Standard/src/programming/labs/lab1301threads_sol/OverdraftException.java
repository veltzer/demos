package programming.labs.lab1301threads_sol;

@SuppressWarnings("serial")
public class OverdraftException extends RuntimeException {
	public OverdraftException(String msg) {
		super(msg);
	}
}
