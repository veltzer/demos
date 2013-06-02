package programming.labs.lab0302syntax;

public abstract class BasicJavaSyntax2 {

	public static void main(String[] args) {
		int[] arr = new int[100];
		double average = 0;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 1001);
			average += arr[i];
		}

		System.out.println("The Average is " + (average / arr.length));

	}
}
