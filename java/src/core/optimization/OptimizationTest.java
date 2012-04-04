package core.optimization;

import java.util.ArrayList;
import java.util.List;

public class OptimizationTest {
	public int xx = 12345678;

	private void lowLevelTest() {
		final int COUNT = 100000000;
		int x = 12345678;
		@SuppressWarnings("unused")
		int y, z, y1, z1;
		{
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < COUNT; ++i) {
				y = x / 4;
				z = x * 2;
			}
			long endtime = System.currentTimeMillis();
			System.out.println("time for /, *: " + (endtime - startTime));
		}
		{
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < COUNT; ++i) {
				y = x >> 2;
				z = x << 1;
			}
			long endtime = System.currentTimeMillis();
			System.out.println("time for << >>: " + (endtime - startTime));
		}
		// {
		// long startTime = System.currentTimeMillis();
		// for (int i = 0; i < COUNT; i += 4)
		// {
		// y = x >> 2;
		// z = x << 1;
		// y1 = x >> 2;
		// z1 = x << 1;
		// }
		// long endtime = System.currentTimeMillis();
		// System.out.println("time for << >> with +=2: "
		// + (endtime - startTime));
		// }
		final int LIST_SIZE = 1000000;
		List<Integer> l = new ArrayList<Integer>(LIST_SIZE);
		for (int i = 0; i < LIST_SIZE; ++i)
			l.add(i);
		{
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < l.size(); ++i) {
				l.get(i);
			}
			long endtime = System.currentTimeMillis();
			System.out.println("time for l.size(): " + (endtime - startTime));
		}
		{
			long startTime = System.currentTimeMillis();
			int size = l.size();
			for (int i = 0; i < size; ++i) {
				l.get(i);
			}
			long endtime = System.currentTimeMillis();
			System.out.println("time for local size: " + (endtime - startTime));
		}
		{
			long startTime = System.currentTimeMillis();
			for (@SuppressWarnings("unused")
			int i : l) {
			}
			long endtime = System.currentTimeMillis();
			System.out.println("time for local size: " + (endtime - startTime));
		}
	}

	private void f() {

	}

	private synchronized void g() {
		// ...
		// ...
		// ...
	}

	private void synchroTest() {
		final int COUNT = 100000000;
		{
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < COUNT; ++i) {
				f();
			}
			long endtime = System.currentTimeMillis();
			System.out.println("time for non-synchronized f(): "
					+ (endtime - startTime));
		}
		{
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < COUNT; ++i) {
				g();
			}
			long endtime = System.currentTimeMillis();
			System.out.println("time for synchronized g(): "
					+ (endtime - startTime));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("starting");
		boolean syncTest = true;
		try {

			OptimizationTest test = new OptimizationTest();
			if (syncTest) {
				test.synchroTest();
			} else {
				test.lowLevelTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");
		;
	}
}
