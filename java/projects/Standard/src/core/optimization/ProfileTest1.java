package core.optimization;

import java.util.LinkedList;
import java.util.List;

/**
 * This small application is used for profiling demonstrations. It creates a
 * large list of random int numbers 0..99, then calculates a reversed list of
 * Math.log(n) for each n in the original list.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public final class ProfileTest1 {
	private List<Integer> numbers;

	private static final int TEST_COUNT = 50000;

	private static final int MAX_VALUE = 100;

	private ProfileTest1() {
		numbers = new LinkedList<Integer>();
	}

	private int getValue(int index) {
		return numbers.get(index);
	}

	private List<Double> reversedLogList() {
		List<Double> result = new LinkedList<Double>();
		for (int i = numbers.size() - 1; i >= 0; --i) {
			int value = getValue(i);
			double log = Math.log(value);
			result.add(log);
		}
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
			ProfileTest1 test = new ProfileTest1();
			test.test();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("done");
	}

}
