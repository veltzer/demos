package core.optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ProfileTest3 {
	private List<Integer> numbers;

	private static final int TEST_COUNT = 50000;

	private static final int MAX_VALUE = 100;

	private double[] cachedLogValues;

	private ProfileTest3() {
		cachedLogValues = new double[MAX_VALUE];
		for (int i = 0; i < MAX_VALUE; ++i) {
			cachedLogValues[i] = Math.log(i);
		}
		numbers = new ArrayList<Integer>(TEST_COUNT);
	}

	/*
	 * private int getValue(int index) { return numbers.get(index); }
	 */
	/*
	 * private List<Double> reversedLogList0() { List<Double> result = new
	 * ArrayList<Double>(TEST_COUNT); for (int i = numbers.size() - 1; i >= 0;
	 * --i) { int value = getValue(i); // double log = logCache.getLog(value);
	 * // double log = Math.log(value); double log = cachedLogValues[value];
	 * result.add(log); } return result; }
	 */
	/*
	 * private List<Double> reversedLogList1() { LinkedList<Double> result = new
	 * LinkedList<Double>(); for (int value : numbers) {
	 * result.addFirst(cachedLogValues[value]); } return result; }
	 */
	private List<Double> reversedLogList() {
		List<Double> result = new ArrayList<Double>(TEST_COUNT);
		for (int value : numbers) {
			result.add(cachedLogValues[value]);
		}
		Collections.reverse(result);
		return result;
	}

	private void test() {
		System.out.println("will test " + TEST_COUNT + " numbers");
		numbers.clear();
		for (int i = 0; i < TEST_COUNT; ++i) {
			int value = (int) (Math.random() * MAX_VALUE);
			numbers.add(value);
		}
		List<Double> result = reversedLogList();
		for (int i = 0; i < 10; ++i) {
			System.out.println(result.get(i));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("starting...");
		try {
			ProfileTest3 test = new ProfileTest3();
			test.test();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("done");
	}

}
