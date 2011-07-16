package com.interbit.exceptionhandling;

public class ExceptionHandling {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		double r1 = 0;
		try {
			r1 = sqrt(9);
		} catch (Exception e) {
			e.printStackTrace();
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
