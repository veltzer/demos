package programming.labs.lab1201collections.interbit.bank;

@SuppressWarnings("serial")
public class OverdraftException extends RuntimeException {
	public OverdraftException(String msg) {
		super(msg);
	}
}
