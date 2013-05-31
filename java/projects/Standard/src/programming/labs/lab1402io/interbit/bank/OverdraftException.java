package programming.labs.lab1402io.interbit.bank;

@SuppressWarnings("serial")
public class OverdraftException extends RuntimeException {
	public OverdraftException(String msg) {
		super(msg);
	}
}
