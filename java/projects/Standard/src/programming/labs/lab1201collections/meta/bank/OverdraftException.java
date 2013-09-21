package programming.labs.lab1201collections.meta.bank;

@SuppressWarnings("serial")
public class OverdraftException extends RuntimeException {
	public OverdraftException(String msg) {
		super(msg);
	}
}
