package programming.labs.lab1301threads.interbit.bank;

@SuppressWarnings("serial")
public class OverdraftException extends Exception {
	public OverdraftException(String msg) {
		super(msg);
	}
}
