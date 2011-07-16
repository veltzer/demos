package com.interbit.solutions;

public class Prog3Lab1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int size = 100;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int) (1000 * Math.random()) + 1;
		}
		long sum = 0;
		for (int x : arr) {
			sum += x;
		}
		double avg = sum / size;
		System.out.println("average is: " + avg);

	}

}
