package programming.solutions.sol1201collections;

public class OverdraftException extends Exception {
	private static final long serialVersionUID = 1L;

	public OverdraftException(String msg){
		super(msg);
	}
}
