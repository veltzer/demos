package core.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProfileTest2 {
	private List<Integer> numbers;

	private static final int TEST_COUNT = 50000;

	private static final int MAX_VALUE = 100;

	private LogCache logCache;

	private class LogCache {
		private Map<Integer, Double> logValues;;

		public LogCache() {
			logValues = new HashMap<Integer, Double>();
			for (int i = 0; i < MAX_VALUE; ++i) {
				logValues.put(i, Math.log(i));
			}
		}

		public double getLog(int value) {
			return logValues.get(value);
		}
	}

	private ProfileTest2() {
		logCache = new LogCache();
		numbers = new ArrayList<Integer>();
	}

	private int getValue(int index) {
		return numbers.get(index);
	}

	private List<Double> reversedLogList() {
		List<Double> result = new ArrayList<Double>(TEST_COUNT);
		for (int i = numbers.size() - 1; i >= 0; --i) {
			int value = getValue(i);
			// cached log value, instead of using Math.log()
			double log = logCache.getLog(value);
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
			ProfileTest2 test = new ProfileTest2();
			test.test();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("done");
	}

}
