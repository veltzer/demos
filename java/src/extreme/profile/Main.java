package extreme.profile;

class Main {
	int do_that(int lim) {
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
			sum += o.do_that(i);
		}
		System.out.println("sum is "+sum);
	}
}
