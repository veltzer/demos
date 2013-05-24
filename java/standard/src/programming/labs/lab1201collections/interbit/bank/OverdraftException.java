package interbit.bank;


public class OverdraftException extends Exception {
	public OverdraftException(String msg){
		super(msg);
	}
}
