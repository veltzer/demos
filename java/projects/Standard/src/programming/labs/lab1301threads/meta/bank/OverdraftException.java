package programming.labs.lab1301threads.meta.bank;

@SuppressWarnings("serial")
public class OverdraftException extends RuntimeException {
	public OverdraftException(String msg) {
		super(msg);
	}
}
