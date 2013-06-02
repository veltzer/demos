package programming.samples.exceptionhandling;

public abstract class ExceptionHandling {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		double r1 = 0;
		try {
			r1 = sqrt(9);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println(r1);
		double r2 = sqrt(-9);
		System.out.println(r2);
	}

	public static double sqrt(double number) throws Exception {
		if (number < 0) {
			throw new Exception();
		}
		return Math.sqrt(number);
	}

}
