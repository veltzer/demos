package programming.labs.lab0302syntax_sol;

public abstract class BasicJavaSyntax2 {

	public static void main(String[] args) {
		int arraySize = Integer.parseInt(args[0]);
		int range = Integer.parseInt(args[1]);
		int[] arr = new int[arraySize];
		double average = 0;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * range);
			average += arr[i];
		}

		System.out.println("The Average is " + (average / arr.length));
	}
}
