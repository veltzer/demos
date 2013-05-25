public class InlineMe {

	private int counter = 0;

	public int getCounter() {
		return counter;
	}
	public void setCounter(int icounter) {
		counter = icounter;
	}

	public void method1() {
		for (int i = 0; i < 1000; i++) {
			addCount();
		}
		System.out.println("counter=" + counter);
	}

	// this method is usually not inlined because of the return value...
	/*
	public int addCount() {
		counter = counter + 1;
		return counter;
	}
	*/
	// this version is usually inlined...
	private void addCount() {
		counter = counter + 1;
	}

	public static void main(String[] args) {
		InlineMe im = new InlineMe();
		im.method1();
	}
}
