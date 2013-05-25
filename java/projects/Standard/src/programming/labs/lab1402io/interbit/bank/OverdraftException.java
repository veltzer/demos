package programming.labs.lab1402io.interbit.bank;

@SuppressWarnings("serial")
public class OverdraftException extends Exception {
	public OverdraftException(String msg) {
		super(msg);
	}
}
