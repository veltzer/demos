package core.profile;

final class Main {
	private Main() {
	}

	private int method(int lim) {
		int sum = 0;
		for (int j = 0; j < lim; j++) {
			sum += j;
		}
		return (sum);
	}

	public static void main(String[] args) {
		Main o = new Main();
		int sum = 0;
		for (int i = 0; i < 1000000; i++) {
			sum += o.method(i);
		}
		System.out.println("sum is " + sum);
	}
}
